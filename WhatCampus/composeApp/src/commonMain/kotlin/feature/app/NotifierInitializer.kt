package feature.app

import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.PayloadData
import core.common.util.defaultDatetimeFormatter
import core.common.util.parse
import core.domain.repository.UserRepository
import core.model.Notice
import feature.app.navigation.WhatcamNavigator
import feature.notice.navigation.NoticeDetailDeepLink
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object NotifierInitializer : KoinComponent {
    private val userRepository: UserRepository by inject()

    fun onApplicationStart() {
        onApplicationStartPlatformSpecific()
        NotifierManager.addListener(object : NotifierManager.Listener {
            override fun onPayloadData(data: PayloadData) {
                super.onPayloadData(data)
                val pushTitle = data["pushTitle"] as String
                val pushMessage = data["pushMessage"] as String

                MainScope().launch {
                    val user = userRepository.flowUser().firstOrNull() ?: return@launch
                    if (user.isPushNotificationAllowed.not()) return@launch

                    NotifierManager.getLocalNotifier().notify(
                        id = pushTitle.hashCode(),
                        title = pushTitle,
                        body = pushMessage,
                        payloadData = data as Map<String, String>
                    )
                }
            }

            override fun onNotificationClicked(data: PayloadData) {
                super.onNotificationClicked(data)
                val noticeId = data["id"] as String
                val noticeTitle = data["title"] as String
                val noticeDatetime = data["datetime"] as String
                val noticeUrl = data["url"] as String

                val notice = Notice(
                    id = noticeId.toLong(),
                    title = noticeTitle,
                    datetime = noticeDatetime.parse(defaultDatetimeFormatter),
                    url = noticeUrl,
                )

                WhatcamNavigator.handleDeeplink(
                    deepLink = NoticeDetailDeepLink(notice = notice)
                )
            }
        })
    }
}

expect fun onApplicationStartPlatformSpecific()
