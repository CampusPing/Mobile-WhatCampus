package core.domain.repository

import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>>
}
