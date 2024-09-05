package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetNoticesByCategoryIdUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(
        universityId: Long,
        categoryId: Long,
    ): Flow<Response<List<Notice>>> = repository
        .flowNoticesByCategoryId(universityId = universityId, noticeCategoryId = categoryId)
        .map { noticesResponse ->
            noticesResponse.map { notices -> notices.sortedByDatetime() }
        }

    private fun List<Notice>.sortedByDatetime(): List<Notice> = sortedByDescending(Notice::datetime)
}
