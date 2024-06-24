@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package feature.university

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetUniversityUseCase
import core.model.University
import feature.university.model.UniversityUiState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
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

    private val _universitySearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val universitySearchQuery = _universitySearchQuery.asStateFlow()

    init {
        universitySearchQuery
            .debounce(SEARCH_DEBOUNCE)
            .map { query -> query.trim() }
            .flatMapLatest { query ->
                getUniversityUseCase(query)
                    .map { universities ->
                        when (val uiState = uiState.value) {
                            is UniversityUiState.Success -> uiState.copy(
                                universities = universities.toPersistentList(),
                                selectedUniversity = uiState.selectedUniversity,
                            )

                            UniversityUiState.Loading -> UniversityUiState.Success(
                                universities = universities.toPersistentList()
                            )
                        }
                    }
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

    fun searchUniversity(query: String) {
        _universitySearchQuery.value = query
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 300L
    }
}
