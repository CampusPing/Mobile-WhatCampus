package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

data class GetAllNoticesUseCase(
    private val noticeRepository: NoticeRepository,
    private val getNoticesByCategoryId: GetNoticesByCategoryIdUseCase,
    private val getNoticesByDepartmentId: GetNoticesByDepartmentIdUseCase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        universityId: Long,
        departmentId: Long,
    ): Flow<Response<List<Notice>>> {
        val universityNoticesFlow = noticeRepository.flowNoticeCategory(universityId = universityId)
            .flatMapLatest { noticeCategoriesResponse ->
                when (noticeCategoriesResponse) {
                    is Response.Success -> noticeCategoriesResponse.body
                        .map { noticeCategory ->
                            getNoticesByCategoryId(
                                universityId = universityId,
                                categoryId = noticeCategory.id
                            )
                        }
                        .combineFlows()
                        .map { responses ->
                            val successfulNotices = responses.filterIsInstance<Response.Success<List<Notice>>>()
                                .flatMap { it.body }

                            Response.Success(successfulNotices)
                        }

                    is Response.Failure -> flowOf(noticeCategoriesResponse)
                }
            }

        val departmentNoticesFlow = getNoticesByDepartmentId(
            universityId = universityId,
            departmentId = departmentId
        )

        return combine(
            universityNoticesFlow,
            departmentNoticesFlow
        ) { universityNotices, departmentNotices ->
            when {
                universityNotices is Response.Failure -> universityNotices
                departmentNotices is Response.Failure -> departmentNotices
                universityNotices is Response.Success && departmentNotices is Response.Success -> {
                    val combinedNotices = (universityNotices.body + departmentNotices.body).distinct()
                    Response.Success(combinedNotices)
                }

                else -> Response.Failure.OtherError(IllegalStateException("Unexpected response state"))
            }
        }
    }

    private fun List<Flow<Response<List<Notice>>>>.combineFlows(): Flow<List<Response<List<Notice>>>> {
        return if (this.isEmpty()) {
            flowOf(emptyList())
        } else {
            combine(this) { it.toList() }
        }
    }
}
