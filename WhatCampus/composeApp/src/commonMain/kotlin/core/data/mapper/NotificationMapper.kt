package core.data.mapper

import core.common.util.parse
import core.data.model.NotificationDto
import core.data.model.NotificationsDto
import core.model.Notice
import core.model.Notification
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
private val notificationDateTimeFormatter = LocalDateTime.Format {
    byUnicodePattern("yyyyMMdd HHmmss")
}

internal fun NotificationsDto.toNotifications(): PersistentList<Notification> = notifications
    .map { it.toNotification() }
    .toPersistentList()

private fun NotificationDto.toNotification(): Notification = Notification.NewNotice(
    notificationId = notificationId,
    content = notificationMessage,
    isRead = isRead,
    receivedDatetime = sendDateTime.parse(notificationDateTimeFormatter),
    notice = Notice(
        id = noticeId,
        title = noticeTitle,
        datetime = noticeDate.paddingTime().parse(notificationDateTimeFormatter),
        url = noticeUrl,
    ),
)

private fun String.paddingTime(): String = "$this 000000"
