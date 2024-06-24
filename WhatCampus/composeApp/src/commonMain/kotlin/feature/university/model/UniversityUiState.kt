package feature.university.model

import core.model.University
import kotlinx.collections.immutable.persistentListOf

sealed interface UniversityUiState {
    data object Loading : UniversityUiState

    data class Success(
        val universities: List<University> = persistentListOf(),
        val selectedUniversity: University? = null,
    ) : UniversityUiState
}
