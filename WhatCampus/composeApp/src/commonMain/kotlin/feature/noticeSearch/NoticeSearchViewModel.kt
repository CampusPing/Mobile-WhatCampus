package feature.noticeSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetFilteredNoticesUseCase
import feature.noticeSearch.model.NoticeSearchUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NoticeSearchViewModel(
    getFilteredNotices: GetFilteredNoticesUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState: MutableStateFlow<NoticeSearchUiState> = MutableStateFlow(NoticeSearchUiState.Loading)
    val uiState: StateFlow<NoticeSearchUiState> = _uiState.asStateFlow()

    private val _noticeSearchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val noticeSearchQuery = _noticeSearchQuery.asStateFlow()

    init {
        noticeSearchQuery
            .debounce(SEARCH_DEBOUNCE)
            .onEach { _uiState.update { NoticeSearchUiState.Loading } }
            .map { query -> query.trim() }
            .flatMapLatest { query ->
                getFilteredNotices(
                    query = query,
                    universityId = 1,
                    departmentId = 1,
                )
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .onEach { searchedNotices -> _uiState.update { NoticeSearchUiState.Success(searchedNotices) } }
            .launchIn(viewModelScope)
    }

    fun searchNotice(query: String) {
        _noticeSearchQuery.update { query }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 600L
    }
}
