package feature.app

import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.PayloadData
import core.model.Notice
import feature.app.navigation.WhatcamNavigator
import feature.notice.navigation.NoticeDetailDeepLink
import io.github.aakira.napier.Napier
import kotlinx.datetime.LocalDateTime

object NotifierInitializer {
    fun onApplicationStart() {
        onApplicationStartPlatformSpecific()
        NotifierManager.addListener(object : NotifierManager.Listener {
            override fun onPayloadData(data: PayloadData) {
                super.onPayloadData(data)
                val pushTitle = data["pushTitle"] as String
                val pushMessage = data["pushMessage"] as String

                NotifierManager.getLocalNotifier().notify(
                    id = pushTitle.hashCode(),
                    title = pushTitle,
                    body = pushMessage,
                    payloadData = data as Map<String, String>
                )
            }

            override fun onNotificationClicked(data: PayloadData) {
                super.onNotificationClicked(data)
                val noticeId = data["id"] as String
                val noticeTitle = data["title"] as String
                val noticeDatetime = data["datetime"] as String
                val noticeUrl = data["url"] as String
                Napier.d { noticeDatetime }

                val notice = Notice(
                    id = noticeId.toLong(),
                    title = noticeTitle,
                    datetime = LocalDateTime(2024, 3, 3, 3, 3, 3),
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
