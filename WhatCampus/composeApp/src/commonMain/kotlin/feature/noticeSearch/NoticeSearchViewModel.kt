package feature.noticeSearch

import androidx.lifecycle.viewModelScope
import core.common.CommonViewModel
import core.domain.repository.SearchQueryRepository
import core.domain.repository.UserRepository
import core.domain.usecase.GetFilteredNoticesUseCase
import core.model.Notice
import core.model.Response
import core.model.User
import feature.noticeSearch.model.NoticeSearchUiState
import io.github.aakira.napier.Napier
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    userRepository: UserRepository,
    private val getFilteredNotices: GetFilteredNoticesUseCase,
    private val searchQueryRepository: SearchQueryRepository,
) : CommonViewModel() {
    private val _uiState = MutableStateFlow(NoticeSearchUiState(isLoading = true))
    val uiState: StateFlow<NoticeSearchUiState> = _uiState.asStateFlow()

    private val _noticeSearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val noticeSearchQuery = _noticeSearchQuery.asStateFlow()

    init {
        userRepository.flowUser()
            .filterNotNull()
            .flatMapLatest { user -> mapUserAndQueryFlow(user) }
            .flatMapLatest { (user, query) -> getFilteredNoticesFlow(query, user) }
            .onEach { response -> handleFilteredNoticesResponse(response) }
            .launchIn(viewModelScope)

        searchQueryRepository.flowSearchQueryHistories()
            .onEach { searchQueries ->
                _uiState.update { state ->
                    state.copy(isLoading = false, searchHistories = searchQueries)
                }
            }
            .launchIn(viewModelScope)
    }


    private fun mapUserAndQueryFlow(user: User): Flow<Pair<User, String>> = noticeSearchQuery
        .debounce(SEARCH_DEBOUNCE)
        .filter { query -> query.isNotBlank() }
        .map { query -> user to query.trim() }

    private fun getFilteredNoticesFlow(
        query: String,
        user: User,
    ): Flow<Response<List<Notice>>> = getFilteredNotices(
        query = query,
        universityId = user.universityId,
        departmentId = user.departmentId,
    )

    private suspend fun NoticeSearchViewModel.handleFilteredNoticesResponse(response: Response<List<Notice>>) {
        when (response) {
            is Response.Success -> _uiState.update { state ->
                Napier.d { "Filtered notices: ${response.body}" }
                state.copy(isLoading = false, searchedNotices = response.body)
            }

            Response.Failure.ClientError -> sendClientErrorEvent()
            Response.Failure.ServerError -> sendServerErrorEvent()
            Response.Failure.NetworkError -> sendNetworkErrorEvent()
            is Response.Failure.OtherError<*> -> sendOtherErrorEvent()
        }
    }

    fun searchNotice(query: String) {
        _noticeSearchQuery.update { query }
    }

    fun deleteSearchHistory(query: String) {
        viewModelScope.launch {
            searchQueryRepository.deleteQueryHistories(query)
            _uiState.update { state ->
                state.copy(searchHistories = state.searchHistories.filter { searchHistory -> searchHistory != query })
            }
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
