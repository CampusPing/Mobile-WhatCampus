package feature.notice

import androidx.lifecycle.ViewModel
import core.model.Notice
import feature.notice.model.NoticeDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoticeDetailViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<NoticeDetailUiState> = MutableStateFlow(NoticeDetailUiState.Success())
    val uiState: StateFlow<NoticeDetailUiState> = _uiState.asStateFlow()

    fun toggleBookmark(notice: Notice) {}
}
