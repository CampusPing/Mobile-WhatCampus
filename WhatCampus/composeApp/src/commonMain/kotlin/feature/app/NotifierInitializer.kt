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

    private const val KEY_PUSH_TITLE = "pushTitle"
    private const val KEY_PUSH_MESSAGE = "pushMessage"
    private const val KEY_NOTICE_ID = "id"
    private const val KEY_NOTICE_TITLE = "title"
    private const val KEY_NOTICE_DATETIME = "datetime"
    private const val KEY_NOTICE_URL = "url"

    fun onApplicationStart() {
        onApplicationStartPlatformSpecific()

        NotifierManager.addListener(object : NotifierManager.Listener {
            override fun onPayloadData(data: PayloadData) {
                super.onPayloadData(data)
                val pushTitle = data[KEY_PUSH_TITLE] as String
                val pushMessage = data[KEY_PUSH_MESSAGE] as String

                MainScope().launch {
                    val user = userRepository.flowUser().firstOrNull() ?: return@launch
                    if (user.isPushNotificationAllowed.not()) return@launch

                    NotifierManager.getLocalNotifier().notify(
                        id = pushTitle.hashCode(),
                        title = pushTitle,
                        body = pushMessage,
                        payloadData = data.toMap(),
                    )
                }
            }

            override fun onNotificationClicked(data: PayloadData) {
                super.onNotificationClicked(data)
                WhatcamNavigator.handleDeeplink(
                    deepLink = NoticeDetailDeepLink(notice = data.toNotice())
                )
            }
        })
    }

    private fun PayloadData.toMap(): Map<String, String> {
        val noticeId = this[KEY_NOTICE_ID] as String
        val noticeTitle = this[KEY_NOTICE_TITLE] as String
        val noticeDatetime = this[KEY_NOTICE_DATETIME] as String
        val noticeUrl = this[KEY_NOTICE_URL] as String

        return mapOf(
            KEY_NOTICE_ID to noticeId,
            KEY_NOTICE_TITLE to noticeTitle,
            KEY_NOTICE_DATETIME to noticeDatetime,
            KEY_NOTICE_URL to noticeUrl
        )
    }

    private fun PayloadData.toNotice(): Notice {
        val noticeId = this[KEY_NOTICE_ID] as String
        val noticeTitle = this[KEY_NOTICE_TITLE] as String
        val noticeDatetime = this[KEY_NOTICE_DATETIME] as String
        val noticeUrl = this[KEY_NOTICE_URL] as String

        return Notice(
            id = noticeId.toLong(),
            title = noticeTitle,
            datetime = noticeDatetime.parse(defaultDatetimeFormatter),
            url = noticeUrl,
        )
    }
}

expect fun onApplicationStartPlatformSpecific()
