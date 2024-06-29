package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetNoticesByCategoryIdUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(id: Long): Flow<List<Notice>> {
        return repository
            .flowNotices(noticeCategoryId = id)
            .map { notices -> notices.sortedByDescending { it.datetime } }
    }
}
