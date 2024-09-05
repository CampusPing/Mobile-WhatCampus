package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

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
                    is Response.Success -> noticeCategoriesResponse.body.asFlow()
                        .flatMapMerge { noticeCategory ->
                            getNoticesByCategoryId(
                                universityId = universityId,
                                categoryId = noticeCategory.id
                            )
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
                universityNotices is Response.Success && departmentNotices is Response.Success -> {
                    val allNotices = universityNotices.body + departmentNotices.body
                    val distinctNotices = allNotices.distinctBy { notice -> notice.id }

                    Response.Success(distinctNotices)
                }

                universityNotices is Response.Failure -> universityNotices
                departmentNotices is Response.Failure -> departmentNotices
                else -> Response.Failure.OtherError(IllegalStateException("Unexpected response state"))
            }
        }
    }
}
