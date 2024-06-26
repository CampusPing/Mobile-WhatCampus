package feature.notice

import androidx.lifecycle.ViewModel
import feature.notice.model.NoticeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoticeViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<NoticeUiState> = MutableStateFlow(NoticeUiState.Loading)
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()
}
