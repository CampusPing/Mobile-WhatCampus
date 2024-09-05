package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.NoticeRepository
import core.domain.usecase.IsBookmarkedNoticeUseCase
import core.model.Notice
import feature.notice.model.NoticeDetailUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoticeDetailViewModel(
    private val noticeRepository: NoticeRepository,
    private val isBookmarkedNotice: IsBookmarkedNoticeUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<NoticeDetailUiState> = MutableStateFlow(NoticeDetailUiState())
    val uiState: StateFlow<NoticeDetailUiState> = _uiState.asStateFlow()

    private val _currentNotice: MutableStateFlow<Notice?> = MutableStateFlow(null)

    init {
        _currentNotice
            .filterNotNull()
            .flatMapLatest { notice ->
                isBookmarkedNotice(notice).map { isBookmarked ->
                    NoticeDetailUiState(bookmarkedNotice = if (isBookmarked) notice else null)
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
            val isBookmarked = isBookmarkedNotice(notice).first()
            if (isBookmarked) {
                noticeRepository.unbookmarkNotice(notice)
            } else {
                noticeRepository.bookmarkNotice(notice)
            }

            _uiState.update { state ->
                state.copy(bookmarkedNotice = if (state.isBookmarked) null else notice)
            }
        }
    }
}
