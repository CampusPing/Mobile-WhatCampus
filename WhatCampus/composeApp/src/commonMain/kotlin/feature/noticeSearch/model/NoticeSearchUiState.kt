package feature.noticeSearch.model

import core.model.Notice
import kotlinx.collections.immutable.persistentListOf

data class NoticeSearchUiState(
    val isLoading: Boolean = false,
    val searchedNotices: List<Notice> = persistentListOf(),
    val searchHistories: List<String> = persistentListOf(),
) {
    val isEmptyResult: Boolean
        get() = searchedNotices.isEmpty()
    val isEmptyHistory: Boolean
        get() = searchHistories.isEmpty()
}
