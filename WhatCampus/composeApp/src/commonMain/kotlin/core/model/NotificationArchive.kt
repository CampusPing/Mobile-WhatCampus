package core.model

sealed class NotificationArchive {
    data class NewNotice(
        val notice: Notice,
        val isRead: Boolean,
    )
}