package feature.noticeSearch.model

import core.model.Notice
import kotlinx.collections.immutable.persistentListOf

data class NoticeSearchUiState(
    val searchQuery: String = "",
    val searchedNotices: List<Notice> = persistentListOf(),
) {
    val isEmptyResult: Boolean
        get() = searchedNotices.isEmpty()
}
