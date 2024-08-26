package core.model

import kotlinx.datetime.LocalDateTime

sealed class NotificationArchive(
    val id: Long,
    val content: String,
    val isRead: Boolean,
    val datetime: LocalDateTime,
) {
    class NewNotice(
        notificationArchiveId: Long,
        title: String,
        isRead: Boolean,
        datetime: LocalDateTime,
        val notice: Notice,
    ) : NotificationArchive(
        id = notificationArchiveId,
        content = title,
        isRead = isRead,
        datetime = datetime,
    )
}
