package feature.notice.model

import core.model.Notice

data class NoticeDetailUiState(
    val bookmarkedNotice: Notice? = null,
) {
    val isBookmarked: Boolean = bookmarkedNotice != null
}
