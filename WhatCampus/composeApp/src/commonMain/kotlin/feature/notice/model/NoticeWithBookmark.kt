package feature.notice.model

import core.model.Notice
import kotlinx.datetime.LocalDateTime

data class NoticeWithBookmark(
    val notice: Notice,
    val isBookmarked: Boolean,
) {
    val id: Long = notice.id
    val title: String = notice.title
    val datetime: LocalDateTime = notice.datetime
    val url: String = notice.url
}
