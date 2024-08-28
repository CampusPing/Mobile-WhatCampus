package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class MemberRegisterRequest(
    val fcmToken: String,
    val campusId: Long,
    val departmentId: Long,
    val categoryIds: List<Long>,
)

@Serializable
internal data class MemberRegisterResponse(
    val memberId: Long,
    val campusId: Long,
    val departmentId: Long,
)
