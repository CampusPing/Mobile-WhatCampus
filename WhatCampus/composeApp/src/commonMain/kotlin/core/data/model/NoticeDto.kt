package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CampusNoticesResponse(
    val campusNotices: List<NoticeResponse>,
)

@Serializable
internal data class DepartmentNoticesResponse(
    val departmentNotices: List<NoticeResponse>,
)

@Serializable
internal data class NoticeResponse(
    val noticeId: Long,
    val noticeTitle: String,
    val noticedAt: String,
    val noticeUrl: String,
)
