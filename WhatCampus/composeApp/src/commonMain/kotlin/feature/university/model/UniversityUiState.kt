package feature.university.model

import core.model.Department
import core.model.NoticeCategory
import core.model.University
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentSet

data class UniversityUiState(
    val universities: List<University> = persistentListOf(),
    val selectedUniversity: University? = null,
    val selectedDepartment: Department? = null,
    val noticeCategories: List<NoticeCategory> = persistentListOf(),
    val selectedNoticeCategories: Set<NoticeCategory> = noticeCategories.toPersistentSet(),
) {
    fun toggleSelectedNoticeCategory(noticeCategory: NoticeCategory): UniversityUiState {
        val selectedNoticeCategories = selectedNoticeCategories.toMutableSet()

        if (noticeCategory in selectedNoticeCategories) {
            selectedNoticeCategories -= noticeCategory
        } else {
            selectedNoticeCategories += noticeCategory
        }

        return copy(selectedNoticeCategories = selectedNoticeCategories)
    }
}
