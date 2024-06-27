package feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import feature.bookmark.model.BookmarkUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BookmarkViewModel(
    getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
) : ViewModel() {
    val uiState = getAllBookmarkedNotices()
        .map { bookmarkedNotices -> BookmarkUiState(notices = bookmarkedNotices) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BookmarkUiState()
        )
    
}
