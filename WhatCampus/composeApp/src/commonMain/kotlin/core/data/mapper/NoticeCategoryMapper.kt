package core.data.mapper

import core.data.model.NoticeCategoriesResponse
import core.data.model.NoticeCategoryResponse
import core.data.model.SubscribedNoticeCategoryResponse
import core.model.NoticeCategory

internal fun NoticeCategoriesResponse.toNoticeCategories(): List<NoticeCategory> {
    return categories.map { it.toNoticeCategory() }
}

private fun NoticeCategoryResponse.toNoticeCategory(): NoticeCategory = NoticeCategory(
    id = categoryId,
    name = categoryName,
    symbolEmoji = categorySymbolEmoji,
)

internal fun List<SubscribedNoticeCategoryResponse>.toNoticeCategories(): List<NoticeCategory> {
    return this.map { it.toNoticeCategory() }
}

private fun SubscribedNoticeCategoryResponse.toNoticeCategory(): NoticeCategory = NoticeCategory(
    id = noticeCategoryId,
    name = noticeCategoryName,
    symbolEmoji = noticeCategorySymbolEmoji,
)
