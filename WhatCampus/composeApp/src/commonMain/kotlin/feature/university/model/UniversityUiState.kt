package feature.university.model

import core.model.Department
import core.model.University
import kotlinx.collections.immutable.persistentListOf

data class UniversityUiState(
    val universities: List<University> = persistentListOf(),
    val selectedUniversity: University? = null,
    val selectedDepartment: Department? = null,
)
