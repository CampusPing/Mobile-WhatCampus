package feature.noticeCategory.model

import core.model.NoticeCategory
import kotlinx.collections.immutable.persistentListOf

data class NoticeCategoryUiState(
    val noticeCategoryList: List<NoticeCategory> = persistentListOf(),
    val selectedNoticeCategories: List<NoticeCategory> = persistentListOf(),
)
