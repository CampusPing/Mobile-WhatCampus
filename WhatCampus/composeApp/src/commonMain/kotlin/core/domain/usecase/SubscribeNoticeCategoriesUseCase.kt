package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

data class SubscribeNoticeCategoriesUseCase(
    private val repository: NoticeRepository,
) {
    suspend operator fun invoke(
        userId: Long,
        noticeCategories: Set<NoticeCategory>,
    ) {
        return repository.subscribeNoticeCategories(
            userId = userId,
            noticeCategories = noticeCategories,
        )
    }
}
