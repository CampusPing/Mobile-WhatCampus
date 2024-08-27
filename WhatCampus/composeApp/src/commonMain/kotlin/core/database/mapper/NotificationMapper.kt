package core.database.mapper

import core.database.entity.NotificationEntity
import core.model.Notice
import core.model.Notification

internal fun NotificationEntity.toNotification(): Notification {
    return Notification.NewNotice(
        notificationId = id,
        content = content,
        receivedDatetime = receivedDatetime,
        isRead = isRead,
        notice = Notice(
            id = noticeId,
            title = noticeTitle,
            datetime = noticeDatetime,
            url = noticeUrl,
        )
    )
}

internal fun Notification.NewNotice.toNotificationEntity(): NotificationEntity {
    return NotificationEntity(
        id = id,
        content = content,
        isRead = isRead,
        receivedDatetime = receivedDatetime,
        noticeId = notice.id,
        noticeTitle = notice.title,
        noticeDatetime = notice.datetime,
        noticeUrl = notice.url,
    )
}
