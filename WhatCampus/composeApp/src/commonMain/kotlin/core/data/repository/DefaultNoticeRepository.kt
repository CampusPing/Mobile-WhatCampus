package core.data.repository

import core.data.common.safeGet
import core.data.common.safePatch
import core.data.mapper.toNoticeCategories
import core.data.mapper.toNotices
import core.data.model.CampusNoticesResponse
import core.data.model.DepartmentNoticesResponse
import core.data.model.NoticeCategoriesResponse
import core.data.model.NoticeCategoriesSubscribeRequest
import core.data.model.SubscribedNoticeCategoriesResponse
import core.data.model.SubscribedNoticeCategoryResponse
import core.database.dao.NoticeDao
import core.database.mapper.toNotice
import core.database.mapper.toNoticeEntity
import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.NoticeCategory
import core.model.Response
import io.ktor.client.HttpClient
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultNoticeRepository(
    private val httpClient: HttpClient,
    private val noticeDao: NoticeDao,
) : NoticeRepository {

    override fun flowNoticesByCategoryId(
        universityId: Long,
        noticeCategoryId: Long,
    ): Flow<Response<List<Notice>>> = flow {
        val requestUrl = "/api/v1/notices/campuses/$universityId/categories/$noticeCategoryId"
        val campusNoticesResponse = httpClient.safeGet<CampusNoticesResponse>(requestUrl)

        emit(campusNoticesResponse.map(CampusNoticesResponse::toNotices))
    }

    override fun flowNoticesByDepartmentId(
        universityId: Long,
        departmentId: Long,
    ): Flow<Response<List<Notice>>> = flow {
        val requestUrl = "/api/v1/notices/campuses/$universityId/departments/$departmentId"
        val departmentNoticesResponse = httpClient.safeGet<DepartmentNoticesResponse>(requestUrl)

        emit(departmentNoticesResponse.map(DepartmentNoticesResponse::toNotices))
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
    ): Flow<Response<List<NoticeCategory>>> = flow {
        val requestUrl = "/api/v1/campuses/$universityId/notices/categories"
        val categoriesResponse = httpClient.safeGet<NoticeCategoriesResponse>(requestUrl)

        emit(categoriesResponse.map(NoticeCategoriesResponse::toNoticeCategories))
    }

    override fun flowSubscribedNoticeCategories(
        userId: Long,
    ): Flow<Response<Set<NoticeCategory>>> = flow {
        val requestUrl = "/api/v1/subscribes/members/$userId"
        val response = httpClient.safeGet<SubscribedNoticeCategoriesResponse>(requestUrl)

        emit(response.map { it.filterSubscribed().toNoticeCategories().toSet() })
    }

    private fun SubscribedNoticeCategoriesResponse.filterSubscribed(): List<SubscribedNoticeCategoryResponse> {
        return categorySubscribes.filter(SubscribedNoticeCategoryResponse::isSubscribed)
    }

    @OptIn(InternalAPI::class)
    override suspend fun subscribeNoticeCategories(
        userId: Long,
        unsubscribedNoticeCategoryIds: List<Long>,
        subscribedNoticeCategoryIds: List<Long>,
    ): Response<Unit> {
        val requestUrl = "/api/v1/subscribes/members/$userId"

        return httpClient.safePatch<Unit>(requestUrl) {
            body = NoticeCategoriesSubscribeRequest.of(
                unsubscribedNoticeCategoryIds = unsubscribedNoticeCategoryIds,
                subscribedNoticeCategoryIds = subscribedNoticeCategoryIds,
            )
        }
    }
}
