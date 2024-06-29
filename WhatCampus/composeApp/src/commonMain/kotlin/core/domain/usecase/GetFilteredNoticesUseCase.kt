package core.domain.usecase

import core.model.Notice
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
    private val getNoticeCategoriesByUniversityId: GetNoticeCategoriesByUniversityIdUseCase,
    private val getNoticesByCategoryIdUseCase: GetNoticesByCategoryIdUseCase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        universityId: Long,
        departmentId: Long,
        query: String,
    ): Flow<List<Notice>> {
        if (query.isBlank()) {
            return flowOf(persistentListOf())
        }

        val universityNoticesFlow = getNoticeCategoriesByUniversityId(universityId)
            .flatMapLatest { noticeCategories ->
                noticeCategories
                    .asFlow()
                    .flatMapMerge { noticeCategory -> getNoticesByCategoryIdUseCase(noticeCategory.id) }
            }

        val departmentNoticesFlow = getNoticesByCategoryIdUseCase(departmentId)

        return combine(
            universityNoticesFlow,
            departmentNoticesFlow
        ) { universityNotices, departmentNotices ->
            (universityNotices + departmentNotices).distinctBy { notice -> notice.id }
        }
            .map { notices -> notices.sortedByDescending { notice -> notice.datetime } }
            .map { notices -> notices.filter { notice -> notice.title.contains(query, ignoreCase = true) } }
    }
}
