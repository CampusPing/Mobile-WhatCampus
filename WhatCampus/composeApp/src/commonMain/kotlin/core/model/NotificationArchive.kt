package core.model

import kotlinx.datetime.LocalDateTime

sealed class NotificationArchive(
    val id: Long,
    val isRead: Boolean,
    val datetime: LocalDateTime,
) {
    class NewNotice(
        notificationArchiveId: Long,
        val notice: Notice,
        isRead: Boolean,
        datetime: LocalDateTime,
    ) : NotificationArchive(
        id = notificationArchiveId,
        isRead = isRead,
        datetime = datetime,
    )
}
