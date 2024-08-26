package core.model

sealed class NotificationArchive(
    val id: Long,
    val isRead: Boolean,
) {
    class NewNotice(
        notificationArchiveId: Long,
        val notice: Notice,
        isRead: Boolean,
    ) : NotificationArchive(
        id = notificationArchiveId,
        isRead = isRead
    )
}
