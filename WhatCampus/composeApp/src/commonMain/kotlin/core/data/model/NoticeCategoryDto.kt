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
    val categorySubscribes: List<SubscribedNoticeCategoryResponse>,
)

@Serializable
internal data class SubscribedNoticeCategoryResponse(
    val noticeCategoryId: Long,
    val noticeCategoryName: String,
    val noticeCategorySymbolEmoji: String,
    val isSubscribed: Boolean,
)

@Serializable
internal data class NoticeCategoriesSubscribeRequest(
    val categories: List<NoticeCategorySubscribeRequest>,
) {
    companion object {
        fun of(
            unsubscribedNoticeCategoryIds: List<Long>,
            subscribedNoticeCategoryIds: List<Long>,
        ): NoticeCategoriesSubscribeRequest {
            val unsubscribedNoticeCategoryRequest = unsubscribedNoticeCategoryIds.map { id ->
                NoticeCategorySubscribeRequest(categoryId = id, isSubscribed = false)
            }
            val subscribedNoticeCategoryRequest = subscribedNoticeCategoryIds.map { id ->
                NoticeCategorySubscribeRequest(categoryId = id, isSubscribed = true)
            }

            return NoticeCategoriesSubscribeRequest(
                categories = unsubscribedNoticeCategoryRequest + subscribedNoticeCategoryRequest
            )
        }
    }
}

@Serializable
internal data class NoticeCategorySubscribeRequest(
    val categoryId: Long,
    val isSubscribed: Boolean,
)
