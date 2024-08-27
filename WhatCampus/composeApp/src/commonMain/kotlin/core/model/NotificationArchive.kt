package core.model

import kotlinx.datetime.LocalDateTime

sealed class NotificationArchive(
    val id: Long,
    val content: String,
    val isRead: Boolean,
    val receivedDatetime: LocalDateTime,
) {
    class NewNotice(
        notificationId: Long,
        content: String,
        isRead: Boolean,
        receivedDatetime: LocalDateTime,
        val notice: Notice,
    ) : NotificationArchive(
        id = notificationId,
        content = content,
        isRead = isRead,
        receivedDatetime = receivedDatetime,
    )
}
