package feature.noticeCategory.model

import core.model.NoticeCategory
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

data class NoticeCategoryUiState(
    val noticeCategoryList: List<NoticeCategory> = persistentListOf(),
    val selectedNoticeCategories: Set<NoticeCategory> = persistentSetOf(),
) {
    fun toggleSelectedNoticeCategory(noticeCategory: NoticeCategory): NoticeCategoryUiState {
        val selectedNoticeCategories = selectedNoticeCategories.toMutableSet()

        if (noticeCategory in selectedNoticeCategories) {
            selectedNoticeCategories -= noticeCategory
        } else {
            selectedNoticeCategories += noticeCategory
        }

        return copy(selectedNoticeCategories = selectedNoticeCategories)
    }
}
