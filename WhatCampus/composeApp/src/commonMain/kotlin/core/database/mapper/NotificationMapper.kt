package core.database.mapper

import core.database.entity.NotificationEntity
import core.model.Notice
import core.model.NotificationArchive

internal fun NotificationEntity.toNotificationArchive(): NotificationArchive {
    return NotificationArchive.NewNotice(
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

internal fun NotificationArchive.NewNotice.toNotificationEntity(): NotificationEntity {
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
