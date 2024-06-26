package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

data class GetAllNoticeCategoryUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(universityId: Long): Flow<List<NoticeCategory>> {
        return repository.flowNoticeCategory(universityId = universityId)
    }
}
