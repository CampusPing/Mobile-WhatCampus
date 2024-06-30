package core.domain.repository

import core.model.Notice
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>>
    fun flowNotices(noticeCategoryId: Long): Flow<List<Notice>>
    fun flowBookmarkedNotices(): Flow<List<Notice>>
    fun bookmarkNotice(notice: Notice): Flow<Unit>
    fun unbookmarkNotice(notice: Notice): Flow<Unit>
    suspend fun unbookmarkNotices(notices: List<Notice>)
    fun flowSubscribedNoticeCategories(userId: Long): Flow<Set<NoticeCategory>>
    fun subscribeNoticeCategories(noticeCategories: Set<NoticeCategory>): Flow<Unit>
}
