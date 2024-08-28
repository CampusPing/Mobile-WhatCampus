package core.domain.repository

import core.model.Notice
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>>
    fun flowNoticesByCategoryId(
        universityId: Long,
        noticeCategoryId: Long,
    ): Flow<List<Notice>>

    fun flowNoticesByDepartmentId(
        universityId: Long,
        departmentId: Long,
    ): Flow<List<Notice>>

    fun flowBookmarkedNotices(): Flow<List<Notice>>
    fun bookmarkNotice(notice: Notice): Flow<Unit>
    fun unbookmarkNotice(notice: Notice): Flow<Unit>
    suspend fun unbookmarkNotices(notices: List<Notice>)
    fun flowSubscribedNoticeCategories(userId: Long): Flow<Set<NoticeCategory>>
    suspend fun subscribeNoticeCategories(
        userId: Long,
        unsubscribedNoticeCategoryIds: List<Long>,
        subscribedNoticeCategoryIds: List<Long>,
    )
}
