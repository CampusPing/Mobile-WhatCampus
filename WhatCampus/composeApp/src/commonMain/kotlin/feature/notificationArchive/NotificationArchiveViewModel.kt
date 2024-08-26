package feature.notificationArchive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.NotificationArchiveRepository
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NotificationArchiveViewModel(
    notificationArchiveRepository: NotificationArchiveRepository,
) : ViewModel() {

    val uiState = notificationArchiveRepository.flowNotificationArchive()
        .map { notificationArchives ->
            NotificationArchiveUiState(
                isLoading = false,
                notificationArchives = notificationArchives,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NotificationArchiveUiState(isLoading = true),
        )
}
