package core.data.repository

import core.data.mapper.toNotices
import core.data.model.CampusNoticesResponse
import core.data.model.DepartmentNoticesResponse
import core.database.dao.NoticeDao
import core.database.mapper.toNotice
import core.database.mapper.toNoticeEntity
import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.NoticeCategory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultNoticeRepository(
    private val httpClient: HttpClient,
    private val noticeDao: NoticeDao,
) : NoticeRepository {
    private val noticeCategories = listOf(
        NoticeCategory(1, "학과", "📚"),
        NoticeCategory(2, "학사", "🎓"),
        NoticeCategory(3, "일반", "📰"),
        NoticeCategory(4, "사회봉사", "🤝"),
        NoticeCategory(5, "등록/장학", "💰"),
        NoticeCategory(6, "학생생활", "🏠"),
        NoticeCategory(7, "글로벌", "🌍"),
        NoticeCategory(8, "진로취업", "👔"),
        NoticeCategory(9, "비교과", "🎨"),
        NoticeCategory(10, "코로나19", "😷"),
    )

    private val subscribedNoticeCategories = mutableSetOf<NoticeCategory>()

    override fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>> {
        return flow {
            emit(noticeCategories)
        }
    }

    override fun flowNoticesByCategoryId(
        universityId: Long,
        noticeCategoryId: Long,
    ): Flow<List<Notice>> = flow {
        val requestUrl = "/api/v1/notices/campuses/$universityId/categories/$noticeCategoryId"
        val campusNoticesResponse = httpClient.get(requestUrl).body<CampusNoticesResponse>()

        emit(campusNoticesResponse.toNotices())
    }

    override fun flowNoticesByDepartmentId(
        universityId: Long,
        departmentId: Long,
    ): Flow<List<Notice>> = flow {
        val requestUrl = "/api/v1/notices/campuses/$universityId/departments/$departmentId"
        val departmentNoticesResponse = httpClient.get(requestUrl).body<DepartmentNoticesResponse>()

        emit(departmentNoticesResponse.toNotices())
    }

    override fun flowBookmarkedNotices(): Flow<List<Notice>> {
        return noticeDao.getAll()
            .map { noticeEntities ->
                noticeEntities.map { entity -> entity.toNotice() }
            }
    }

    override fun bookmarkNotice(notice: Notice): Flow<Unit> {
        return flow {
            emit(noticeDao.insert(notice = notice.toNoticeEntity()))
        }
    }

    override fun unbookmarkNotice(notice: Notice): Flow<Unit> {
        return flow {
            emit(noticeDao.delete(notice = notice.toNoticeEntity()))
        }
    }

    override suspend fun unbookmarkNotices(notices: List<Notice>) {
        notices.forEach {
            noticeDao.delete(notice = it.toNoticeEntity())
        }
    }

    override fun flowSubscribedNoticeCategories(userId: Long): Flow<Set<NoticeCategory>> {
        return flow {
            emit(subscribedNoticeCategories.toSet())
        }
    }

    override suspend fun subscribeNoticeCategories(userId: Long, noticeCategories: Set<NoticeCategory>) {
        subscribedNoticeCategories.clear()
        subscribedNoticeCategories.addAll(noticeCategories)
    }
}
