@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package feature.university

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetNoticeCategoriesByUniversityIdUseCase
import core.domain.usecase.GetSubscribedNoticeCategoriesUseCase
import core.domain.usecase.GetUniversityUseCase
import core.domain.usecase.SubscribeNoticeCategoriesUseCase
import core.model.Department
import core.model.NoticeCategory
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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class UniversityViewModel(
    getUniversityUseCase: GetUniversityUseCase,
    private val getNoticeCategoriesByUniversityId: GetNoticeCategoriesByUniversityIdUseCase,
    private val getSubscribedNoticeCategories: GetSubscribedNoticeCategoriesUseCase,
    private val subscribeNoticeCategories: SubscribeNoticeCategoriesUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow(UniversityUiState())
    val uiState = _uiState.asStateFlow()

    private val _universitySearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val universitySearchQuery = _universitySearchQuery.asStateFlow()

    private val _departmentSearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val departmentSearchQuery = _departmentSearchQuery.asStateFlow()

    init {
        fetchNoticeCategories(universityId = 1L)

        universitySearchQuery
            .debounce(SEARCH_DEBOUNCE)
            .map { query -> query.trim() }
            .flatMapLatest { query ->
                getUniversityUseCase(query).onEach { universities ->
                    _uiState.update { state ->
                        state.copy(
                            universities = universities.toPersistentList(),
                            selectedUniversity = state.selectedUniversity,
                        )
                    }
                }
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .launchIn(viewModelScope)
    }

    fun selectUniversity(university: University) {
        _uiState.update { state ->
            state.copy(selectedUniversity = university)
        }
    }

    fun searchUniversity(query: String) {
        _universitySearchQuery.value = query
    }

    fun selectDepartment(department: Department) {
        _uiState.update { state ->
            state.copy(selectedDepartment = department)
        }
    }

    fun searchDepartment(query: String) {
        _departmentSearchQuery.value = query
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 300L
    }
}
