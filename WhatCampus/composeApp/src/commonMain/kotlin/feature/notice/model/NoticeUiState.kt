package feature.notice.model

import core.model.NoticeCategory
import core.model.User
import kotlinx.collections.immutable.persistentListOf

data class NoticeUiState(
    val noticeCategories: List<NoticeCategory> = persistentListOf(),
    val selectedCategory: NoticeCategory? = noticeCategories.firstOrNull(),
    val notices: List<NoticeWithBookmark> = persistentListOf(),
    val user: User? = null,
)
