package feature.university

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetUniversityUseCase
import core.model.University
import feature.university.model.UniversityUiState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class UniversityViewModel(
    getUniversityUseCase: GetUniversityUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<UniversityUiState>(UniversityUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getUniversityUseCase()
            .map { universities ->
                UniversityUiState.Success(
                    universities = universities.toPersistentList()
                )
            }
            .catch { throwable ->
                _errorFlow.emit(throwable)
            }
            .onEach { universityUiState ->
                _uiState.value = universityUiState
            }
            .launchIn(viewModelScope)
    }

    fun selectUniversity(university: University) {
        val state = uiState.value
        if (state !is UniversityUiState.Success) return

        _uiState.update {
            state.copy(selectedUniversity = university)
        }
    }
}
