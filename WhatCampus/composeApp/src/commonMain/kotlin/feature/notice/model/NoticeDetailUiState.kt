package feature.notice.model

import core.model.Notice

sealed class NoticeDetailUiState {
    data object Loading : NoticeDetailUiState()

    data class Success(
        val bookmarkedNotice: Notice? = null,
    ) : NoticeDetailUiState() {
        val isBookmarked: Boolean = bookmarkedNotice != null
    }
}
