package core.model

sealed class NotificationArchive(
    val id: Long,
) {
    class NewNotice(
        notificationArchiveId: Long,
        val notice: Notice,
        val isRead: Boolean,
    ) : NotificationArchive(id = notificationArchiveId)
}
