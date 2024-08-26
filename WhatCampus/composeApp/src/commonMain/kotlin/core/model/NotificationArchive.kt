package core.model

sealed class NotificationArchive {
    data class NewNotice(
        val id: Long,
        val notice: Notice,
        val isRead: Boolean,
    ) : NotificationArchive()
}