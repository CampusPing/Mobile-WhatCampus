package feature.noticeCategory.model

import core.model.NoticeCategory
import core.model.User
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

data class NoticeCategoryUiState(
    val noticeCategories: List<NoticeCategory> = persistentListOf(),
    val subscribedNoticeCategories: Set<NoticeCategory> = persistentSetOf(),
    val user: User? = null,
) {
    fun toggleSelectedNoticeCategory(noticeCategory: NoticeCategory): NoticeCategoryUiState {
        val selectedNoticeCategories = subscribedNoticeCategories.toMutableSet()

        if (noticeCategory in selectedNoticeCategories) {
            selectedNoticeCategories -= noticeCategory
        } else {
            selectedNoticeCategories += noticeCategory
        }

        return copy(subscribedNoticeCategories = selectedNoticeCategories)
    }
}
