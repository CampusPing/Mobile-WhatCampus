package core.domain.usecase

import core.model.Notice
import core.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetFilteredNoticesUseCase(
    private val getAllNoticesUseCase: GetAllNoticesUseCase,
) {
    operator fun invoke(
        universityId: Long,
        departmentId: Long,
        query: String,
    ): Flow<Response<List<Notice>>> {
        val allNotices = getAllNoticesUseCase(universityId, departmentId)

        if (query.isBlank()) {
            return allNotices
        }

        return allNotices.map { response ->
            when (response) {
                is Response.Success -> Response.Success(body = response.body
                    .sortedByDescending { notice -> notice.datetime }
                    .filter { notice -> notice.title.contains(query, ignoreCase = true) })

                is Response.Failure -> response
            }
        }
    }
}
