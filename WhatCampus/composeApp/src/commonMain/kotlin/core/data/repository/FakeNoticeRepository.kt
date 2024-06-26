package core.data.repository

import core.domain.repository.NoticeRepository
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoticeRepository : NoticeRepository {
    private val noticeCategories = listOf(
        NoticeCategory(1, "학과"),
        NoticeCategory(2, "학사"),
        NoticeCategory(3, "일반"),
        NoticeCategory(4, "사회봉사"),
        NoticeCategory(5, "등록/장학"),
        NoticeCategory(6, "학생생활"),
        NoticeCategory(7, "글로벌"),
        NoticeCategory(8, "진로취업"),
        NoticeCategory(9, "비교과"),
        NoticeCategory(10, "코로나19"),
    )

    override fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>> {
        return flow {
            emit(noticeCategories)
        }
    }
}
