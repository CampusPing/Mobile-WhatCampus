package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

data class GetSubscribedNoticeCategoriesUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(userId: Long): Flow<Set<NoticeCategory>> {
        return repository.flowSubscribedNoticeCategories(userId = userId)
    }
}
