package feature.noticeSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.SearchQueryRepository
import core.domain.usecase.SearchNoticesUseCase
import core.domain.repository.UserRepository
import core.domain.usecase.GetFilteredNoticesUseCase
import feature.noticeSearch.model.NoticeSearchUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NoticeSearchViewModel(
    private val getFilteredNotices: GetFilteredNoticesUseCase,
    userRepository: UserRepository,
    searchNotices: SearchNoticesUseCase,
    private val searchQueryRepository: SearchQueryRepository,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState: MutableStateFlow<NoticeSearchUiState> = MutableStateFlow(NoticeSearchUiState.Loading)
    val uiState: StateFlow<NoticeSearchUiState> = _uiState.asStateFlow()

    private val _noticeSearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val noticeSearchQuery = _noticeSearchQuery.asStateFlow()

    init {
        userRepository.flowUser()
            .filterNotNull()
            .flatMapLatest { user ->
                noticeSearchQuery
                    .debounce(SEARCH_DEBOUNCE)
                    .onEach { _uiState.update { NoticeSearchUiState.Loading } }
                    .map { query -> query.trim() to user }
            }
            .flatMapLatest { (query, user) ->
                getFilteredNotices(
                    query = query,
                    universityId = user.universityId,
                    departmentId = user.departmentId,
                )
            }
            .launchIn(viewModelScope)

        searchQueryRepository.flowSearchQueryHistories()
            .catch { throwable -> _errorFlow.emit(throwable) }
            .onEach { searchQueries ->
                _uiState.update { it.copy(isLoading = false, searchHistories = searchQueries) }
            }
            .launchIn(viewModelScope)
    }

    fun searchNotice(query: String) {
        _noticeSearchQuery.update { query }
    }

    fun deleteSearchHistory(query: String) {
        viewModelScope.launch {
            searchQueryRepository.deleteQueryHistories(query)
            _uiState.update { it.copy(searchHistories = it.searchHistories.filter { it != query }) }
        }
    }

    fun clearSearchHistories() {
        viewModelScope.launch {
            searchQueryRepository.deleteQueryHistories()
            _uiState.update { it.copy(searchHistories = persistentListOf()) }
        }
    }

    fun addSearchHistory() {
        viewModelScope.launch {
            searchQueryRepository.addSearchQueryHistory(query = noticeSearchQuery.value)
        }

    }

    companion object {
        private const val SEARCH_DEBOUNCE = 600L
    }
}
