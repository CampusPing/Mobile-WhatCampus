package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class NotificationsDto(
    val notifications: List<NotificationDto>,
)

@Serializable
internal data class NotificationDto(
    val notificationId: Long,
    val notificationMessage: String,
    val isRead: Boolean,
    val sendDateTime: String,
    val noticeId: Long,
    val noticeTitle: String,
    val noticeDate: String,
    val noticeUrl: String,
)
