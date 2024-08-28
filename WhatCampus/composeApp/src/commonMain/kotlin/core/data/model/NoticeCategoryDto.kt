package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class NoticeCategoriesResponse(
    val categories: List<NoticeCategoryResponse>,
)

@Serializable
internal data class NoticeCategoryResponse(
    val categoryId: Long,
    val categoryName: String,
    val categorySymbolEmoji: String,
)

@Serializable
internal data class SubscribedNoticeCategoriesResponse(
    val categorySubscribes: List<SubscribedNoticeCategoryResponse>
)

@Serializable
internal data class SubscribedNoticeCategoryResponse(
    val noticeCategoryId: Long,
    val noticeCategoryName: String,
    val noticeCategorySymbolEmoji: String,
    val isSubscribed: Boolean,
)
