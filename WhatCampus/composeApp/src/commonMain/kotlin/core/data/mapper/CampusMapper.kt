package core.data.mapper

import core.data.model.CampusResponse
import core.data.model.CampusesResponse
import core.data.model.DepartmentResponse
import core.data.model.DepartmentsResponse
import core.model.Department
import core.model.University

internal fun CampusesResponse.toUniversities(): List<University> {
    return campuses.map { it.toUniversity() }
}

private fun CampusResponse.toUniversity(): University = University(
    id = campusId,
    name = campusName,
    departments = departments.toDepartments(),
)

private fun DepartmentsResponse.toDepartments(): List<Department> {
    return departments.map { it.toDepartment() }
}

private fun DepartmentResponse.toDepartment(): Department = Department(
    id = departmentId,
    name = departmentName,
)
