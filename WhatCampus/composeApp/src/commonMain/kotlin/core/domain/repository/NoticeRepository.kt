package core.domain.repository

import core.model.Notice
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>>
    fun flowNotices(noticeCategoryId: Long): Flow<List<Notice>>
}
