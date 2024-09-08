package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class NotificationsDto(
    val notifications: List<NotificationDto>,
)

@Serializable
internal data class NotificationDto(
    val notificationId: Long,
    val notificationContent: String,
    val isRead: Boolean,
    val sendDateTime: String,
    val noticeId: Long,
    val noticeTitle: String,
    val noticeDateTime: String,
    val noticeUrl: String,
)
