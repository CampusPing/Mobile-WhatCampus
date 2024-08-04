package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetNoticesByDepartmentIdUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(
        universityId: Long,
        departmentId: Long,
    ): Flow<List<Notice>> = repository
        .flowNoticesByDepartmentId(universityId = universityId, departmentId = departmentId)
        .map { notices -> notices.sortedByDescending { it.datetime } }
}

