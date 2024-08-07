package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetNoticesByCategoryIdUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(
        universityId: Long,
        categoryId: Long,
    ): Flow<List<Notice>> = repository
        .flowNoticesByCategoryId(universityId = universityId, noticeCategoryId = categoryId)
        .map { notices -> notices.sortedByDescending { it.datetime } }
}
