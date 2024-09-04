package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Response

data class SubscribeNoticeCategoriesUseCase(
    private val repository: NoticeRepository,
) {
    suspend operator fun invoke(
        userId: Long,
        allNoticeCategoryIds: List<Long>,
        subscribedNoticeCategoryIds: List<Long>,
    ): Response<Unit> {
        val unsubscribedNoticeCategoryIds = allNoticeCategoryIds - subscribedNoticeCategoryIds.toSet()

        return repository.subscribeNoticeCategories(
            userId = userId,
            unsubscribedNoticeCategoryIds = unsubscribedNoticeCategoryIds,
            subscribedNoticeCategoryIds = subscribedNoticeCategoryIds,
        )
    }
}
