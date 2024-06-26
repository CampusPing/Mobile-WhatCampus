package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetAllNoticeUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(noticeCategoryId: Long): Flow<List<Notice>> {
        return repository
            .flowNotices(noticeCategoryId = noticeCategoryId)
            .map { notices -> notices.sortedByDescending { it.datetime } }
    }
}
