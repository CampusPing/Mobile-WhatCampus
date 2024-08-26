package feature.notificationArchive

import androidx.lifecycle.ViewModel
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationArchiveViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationArchiveUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()
}
