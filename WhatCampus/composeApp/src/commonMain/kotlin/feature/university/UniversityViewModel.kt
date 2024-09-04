@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package feature.university

import androidx.lifecycle.viewModelScope
import core.common.CommonViewModel
import core.domain.repository.NoticeRepository
import core.domain.repository.UniversityRepository
import core.domain.repository.UserRepository
import core.model.Department
import core.model.NoticeCategory
import core.model.Response
import core.model.University
import feature.university.model.UniversityUiEvent
import feature.university.model.UniversityUiState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentSet
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
import kotlinx.coroutines.launch

class UniversityViewModel(
    universityRepository: UniversityRepository,
    private val noticeRepository: NoticeRepository,
    private val userRepository: UserRepository,
) : CommonViewModel() {
    private val _uiEvent = MutableSharedFlow<UniversityUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiState = MutableStateFlow(UniversityUiState())
    val uiState = _uiState.asStateFlow()

    private val _universitySearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val universitySearchQuery = _universitySearchQuery.asStateFlow()

    init {
        universitySearchQuery
            .debounce(SEARCH_DEBOUNCE)
            .map { query -> query.trim() }
            .flatMapLatest { query ->
                universityRepository.flowUniversity(query = query).onEach { universities ->
                    _uiState.update { state ->
                        state.copy(
                            universities = universities.toPersistentList(),
                            selectedUniversity = state.selectedUniversity,
                        )
                    }
                }
            }
            .catch { _ -> _uiEvent.emit(UniversityUiEvent.UniversityLoadFailed) }
            .launchIn(viewModelScope)
    }

    private fun fetchNoticeCategories(universityId: Long) {
        noticeRepository.flowNoticeCategory(universityId = universityId)
            .onEach { noticeCategories ->
                _uiState.update { state ->
                    state.copy(
                        noticeCategories = noticeCategories,
                        selectedNoticeCategories = noticeCategories.toPersistentSet(),
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun selectUniversity(university: University) {
        _uiState.update { state ->
            state.copy(selectedUniversity = university)
        }
        fetchNoticeCategories(universityId = university.id)
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
        _uiState.update { state ->
            state.copy(departmentSearchQuery = query)
        }
    }

    fun toggleNoticeCategory(noticeCategory: NoticeCategory) {
        _uiState.update { state ->
            state.toggleSelectedNoticeCategory(noticeCategory)
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val selectedUniversity = uiState.value.selectedUniversity
            val selectedDepartment = uiState.value.selectedDepartment

            if (selectedUniversity == null || selectedDepartment == null) {
                sendOtherErrorEvent()
                return@launch
            }

            val userCreateResponse = userRepository.createUser(
                universityId = selectedUniversity.id,
                universityName = selectedUniversity.name,
                departmentId = selectedDepartment.id,
                departmentName = selectedDepartment.name,
                noticeCategoryIds = uiState.value.selectedNoticeCategories.map(NoticeCategory::id)
            )

            when (userCreateResponse) {
                is Response.Success -> _uiEvent.emit(UniversityUiEvent.UserSaveSuccess)
                Response.Failure.ClientError -> sendClientErrorEvent()
                Response.Failure.ServerError -> sendServerErrorEvent()
                Response.Failure.NetworkError -> sendNetworkErrorEvent()
                is Response.Failure.OtherError<*> -> sendOtherErrorEvent()
            }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 300L
    }
}
