package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

data class SubscribeNoticeCategoriesUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(noticeCategories: Set<NoticeCategory>): Flow<Unit> {
        return repository.subscribeNoticeCategories(noticeCategories = noticeCategories)
    }
}
