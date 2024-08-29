package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CampusesResponse(
    val campuses: List<CampusResponse> = emptyList(),
)

@Serializable
internal data class CampusResponse(
    val campusId: Long,
    val campusName: String,
    val departments: DepartmentsResponse,
)

@Serializable
internal data class DepartmentsResponse(
    val departments: List<DepartmentResponse> = emptyList(),
)

@Serializable
internal data class DepartmentResponse(
    val departmentId: Long,
    val departmentName: String,
    val departmentUrl: String,
)
