package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.BookmarkNoticeUseCase
import core.domain.usecase.UnbookmarkNoticeUseCase
import core.model.Notice
import feature.notice.model.NoticeDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NoticeDetailViewModel(
    private val bookmarkNoticeUseCase: BookmarkNoticeUseCase,
    private val unbookmarkNoticeUseCase: UnbookmarkNoticeUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<NoticeDetailUiState> = MutableStateFlow(NoticeDetailUiState.Success())
    val uiState: StateFlow<NoticeDetailUiState> = _uiState.asStateFlow()

    fun toggleBookmark(notice: Notice) {
        val state = uiState.value
        if (state !is NoticeDetailUiState.Success) return

        flow {
            emit(state.isBookmarked)
        }
            .onEach { isBookmarked ->
                if (isBookmarked) {
                    unbookmarkNoticeUseCase(notice)
                        .collectLatest { isSuccess ->
                            if (!isSuccess) return@collectLatest
                            _uiState.update { state.copy(bookmarkedNotice = null) }
                        }
                } else {
                    bookmarkNoticeUseCase(notice)
                        .collectLatest { isSuccess ->
                            if (!isSuccess) return@collectLatest
                            _uiState.update { state.copy(bookmarkedNotice = notice) }
                        }
                }
            }.launchIn(viewModelScope)
    }
}
