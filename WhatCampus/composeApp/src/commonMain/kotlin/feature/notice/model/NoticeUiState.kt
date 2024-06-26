package feature.notice.model

import core.model.NoticeCategory
import kotlinx.collections.immutable.persistentListOf

sealed class NoticeUiState {
    data object Loading : NoticeUiState()

    data class Success(
        val noticeCategories: List<NoticeCategory> = persistentListOf(),
        val selectedCategory: NoticeCategory? = noticeCategories.firstOrNull(),
    ) : NoticeUiState()
}
