package feature.noticeSearch.model

import core.model.Notice
import kotlinx.collections.immutable.persistentListOf

sealed interface NoticeSearchUiState {
    data object Loading : NoticeSearchUiState

    data class Success(
        val searchedNotices: List<Notice> = persistentListOf(),
    ) : NoticeSearchUiState {
        val isEmptyResult: Boolean
            get() = searchedNotices.isEmpty()
    }
}
