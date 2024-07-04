package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

data class SubscribeNoticeCategoriesUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(
        userId: Long,
        noticeCategories: Set<NoticeCategory>,
    ): Flow<Unit> {
        return repository.subscribeNoticeCategories(
            userId = userId,
            noticeCategories = noticeCategories,
        )
    }
}
