package core.database.mapper

import core.database.entity.NoticeEntity
import core.model.Notice

internal fun NoticeEntity.toNotice(): Notice = Notice(
    id = id,
    title = title,
    datetime = datetime,
    url = url,
)

internal fun Notice.toNoticeEntity(): NoticeEntity = NoticeEntity(
    id = id,
    title = title,
    datetime = datetime,
    url = url,
)
