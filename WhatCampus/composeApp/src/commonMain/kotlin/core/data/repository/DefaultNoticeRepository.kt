package core.data.repository

import core.data.mapper.toNoticeCategories
import core.data.mapper.toNotices
import core.data.model.CampusNoticesResponse
import core.data.model.DepartmentNoticesResponse
import core.data.model.NoticeCategoriesResponse
import core.data.model.SubscribedNoticeCategoriesResponse
import core.data.model.SubscribedNoticeCategoryResponse
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
    private val subscribedNoticeCategories = mutableSetOf<NoticeCategory>()

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

    override fun flowNoticeCategory(
        universityId: Long,
    ): Flow<List<NoticeCategory>> = flow {
        val requestUrl = "/api/v1/campuses/$universityId/notices/categories"
        val categoriesResponse = httpClient.get(requestUrl).body<NoticeCategoriesResponse>()

        emit(categoriesResponse.toNoticeCategories())
    }

    override fun flowSubscribedNoticeCategories(
        userId: Long,
    ): Flow<Set<NoticeCategory>> = flow {
        val requestUrl = "/api/v1/subscribes/members/$userId"
        val noticeCategoriesResponse = httpClient.get(requestUrl).body<SubscribedNoticeCategoriesResponse>()
        val subscribedNoticeCategoriesResponse = noticeCategoriesResponse.filterSubscribed()

        emit(subscribedNoticeCategoriesResponse.toNoticeCategories().toSet())
    }

    private fun SubscribedNoticeCategoriesResponse.filterSubscribed(): List<SubscribedNoticeCategoryResponse> {
        return categorySubscribes.filter(SubscribedNoticeCategoryResponse::isSubscribed)
    }

    override suspend fun subscribeNoticeCategories(userId: Long, noticeCategories: Set<NoticeCategory>) {
        subscribedNoticeCategories.clear()
        subscribedNoticeCategories.addAll(noticeCategories)
    }
}
