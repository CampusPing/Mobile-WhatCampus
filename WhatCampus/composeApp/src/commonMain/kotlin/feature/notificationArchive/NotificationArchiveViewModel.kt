package feature.notificationArchive

import androidx.lifecycle.ViewModel
import core.domain.repository.NotificationArchiveRepository
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationArchiveViewModel(
    notificationArchiveRepository: NotificationArchiveRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationArchiveUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()
}
