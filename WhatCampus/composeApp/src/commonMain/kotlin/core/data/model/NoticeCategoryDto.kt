package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class NoticeCategoriesResponse(
    val categories: List<NoticeCategoryResponse>
)

@Serializable
internal data class NoticeCategoryResponse(
    val categoryId: Long,
    val categoryName: String,
    val categorySymbolEmoji: String,
)
