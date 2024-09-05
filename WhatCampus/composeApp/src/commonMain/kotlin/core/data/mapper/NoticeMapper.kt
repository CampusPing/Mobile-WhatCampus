package core.data.mapper

import core.common.util.parse
import core.data.model.CampusNoticesResponse
import core.data.model.DepartmentNoticesResponse
import core.data.model.NoticeResponse
import core.model.Notice
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
private val datetimeFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy-MM-dd HHmmss")
}

internal fun CampusNoticesResponse.toNotices(): List<Notice> = campusNotices.map { it.toNotice() }

internal fun DepartmentNoticesResponse.toNotices(): List<Notice> = departmentNotices.map { it.toNotice() }

private fun NoticeResponse.toNotice(): Notice = Notice(
    id = noticeId,
    title = noticeTitle,
    datetime = noticedAt.paddingTime().parse(formatter = datetimeFormatter),
    url = noticeUrl,
)

private fun String.paddingTime(): String = "$this 000000"
