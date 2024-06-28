package feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.UnbookmarkNoticesUseCase
import core.model.Notice
import feature.bookmark.model.BookmarkUiState
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkViewModel(
    getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
    private val unbookmarkNotices: UnbookmarkNoticesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarkUiState())
    val uiState: StateFlow<BookmarkUiState> = _uiState.asStateFlow()

    init {
        getAllBookmarkedNotices()
            .map { bookmarkedNotices ->
                _uiState.value = _uiState.value.copy(notices = bookmarkedNotices)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BookmarkUiState()
            )
            .launchIn(viewModelScope)
    }

    fun markBookmarkForDelete(bookmarkedNotice: Notice) {
        if (bookmarkedNotice in uiState.value.markedNoticesForDelete) {
            _uiState.update { uiState ->
                uiState.copy(markedNoticesForDelete = uiState.markedNoticesForDelete - bookmarkedNotice)
            }
        } else {
            _uiState.update { uiState ->
                uiState.copy(markedNoticesForDelete = uiState.markedNoticesForDelete + bookmarkedNotice)
            }
        }
    }

    fun clearMarkedNoticesForDelete() {
        _uiState.update { uiState ->
            uiState.copy(markedNoticesForDelete = persistentSetOf())
        }
    }

    fun markAllNoticesForDelete() {
        _uiState.update { uiState ->
            uiState.copy(markedNoticesForDelete = uiState.notices.toPersistentSet())
        }
    }

    fun unbookmarkNotices() {
        viewModelScope.launch {
            unbookmarkNotices(notices = uiState.value.markedNoticesForDelete.toList())
        }
    }
}
