package core.domain.repository

import core.model.Notice
import core.model.NoticeCategory
import core.model.Response
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun flowNoticesByCategoryId(
        universityId: Long,
        noticeCategoryId: Long,
    ): Flow<Response<List<Notice>>>

    fun flowNoticesByDepartmentId(
        universityId: Long,
        departmentId: Long,
    ): Flow<Response<List<Notice>>>

    fun flowBookmarkedNotices(): Flow<List<Notice>>

    suspend fun bookmarkNotice(notice: Notice)

    suspend fun unbookmarkNotice(notice: Notice)

    suspend fun unbookmarkNotices(notices: List<Notice>)

    fun flowNoticeCategory(universityId: Long): Flow<Response<List<NoticeCategory>>>

    fun flowSubscribedNoticeCategories(userId: Long): Flow<Response<Set<NoticeCategory>>>

    suspend fun subscribeNoticeCategories(
        userId: Long,
        unsubscribedNoticeCategoryIds: List<Long>,
        subscribedNoticeCategoryIds: List<Long>,
    ): Response<Unit>
}
