package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.Response
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

data class GetFilteredNoticesUseCase(
    private val noticeRepository: NoticeRepository,
    private val getNoticesByCategoryIdUseCase: GetNoticesByCategoryIdUseCase,
    private val getNoticesByDepartmentIdUseCase: GetNoticesByDepartmentIdUseCase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        universityId: Long,
        departmentId: Long,
        query: String,
    ): Flow<Response<List<Notice>>> {
        if (query.isBlank()) {
            return flowOf(Response.Success(persistentListOf()))
        }

        val universityNoticesFlow = noticeRepository.flowNoticeCategory(universityId = universityId)
            .flatMapLatest { noticeCategoriesResponse ->
                when (noticeCategoriesResponse) {
                    is Response.Success -> noticeCategoriesResponse.body.asFlow()
                        .flatMapMerge { noticeCategory ->
                            getNoticesByCategoryIdUseCase(
                                universityId = universityId,
                                categoryId = noticeCategory.id
                            )
                        }

                    is Response.Failure -> flowOf(noticeCategoriesResponse)
                }
            }

        val departmentNoticesFlow = getNoticesByDepartmentIdUseCase(
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
                    val combinedNotices =
                        (universityNotices.body + departmentNotices.body).distinctBy { notice -> notice.id }
                    Response.Success(combinedNotices)
                }

                else -> Response.Failure.OtherError(IllegalStateException("Unexpected response state"))
            }
        }
            .map { response ->
                when (response) {
                    is Response.Success -> Response.Success(response.body
                        .sortedByDescending { notice -> notice.datetime }
                        .filter { notice -> notice.title.contains(query, ignoreCase = true) })

                    is Response.Failure -> response
                }
            }
    }
}
