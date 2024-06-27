package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.BookmarkNoticeUseCase
import core.domain.usecase.IsBookmarkedNoticeUseCase
import core.domain.usecase.UnbookmarkNoticeUseCase
import core.model.Notice
import feature.notice.model.NoticeDetailUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoticeDetailViewModel(
    private val isBookmarkedNoticeUseCase: IsBookmarkedNoticeUseCase,
    private val bookmarkNoticeUseCase: BookmarkNoticeUseCase,
    private val unbookmarkNoticeUseCase: UnbookmarkNoticeUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<NoticeDetailUiState> = MutableStateFlow(NoticeDetailUiState.Success())
    val uiState: StateFlow<NoticeDetailUiState> = _uiState.asStateFlow()

    private val _currentNotice: MutableStateFlow<Notice?> = MutableStateFlow(null)

    init {
        _currentNotice
            .filterNotNull()
            .flatMapLatest { notice ->
                isBookmarkedNoticeUseCase(notice).map { isBookmarked ->
                    NoticeDetailUiState.Success(bookmarkedNotice = if (isBookmarked) notice else null)
                }
            }
            .onEach { newState -> _uiState.value = newState }
            .launchIn(viewModelScope)
    }

    fun setup(notice: Notice) {
        _currentNotice.value = notice
    }

    fun toggleBookmark(notice: Notice) {
        viewModelScope.launch {
            val isBookmarked = isBookmarkedNoticeUseCase(notice).first()

            if (isBookmarked) {
                unbookmarkNoticeUseCase(notice).collect()
            } else {
                bookmarkNoticeUseCase(notice).collect()
            }
        }
    }
}
