package feature.notificationArchive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.NotificationArchiveRepository
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationArchiveViewModel(
    private val notificationArchiveRepository: NotificationArchiveRepository,
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

    fun turnOffNewNotification() {
        viewModelScope.launch {
            notificationArchiveRepository.updateHasNewNotification(hasNewNotification = false)
        }
    }

    fun readNotification(notificationId: Long) {
        viewModelScope.launch {
            notificationArchiveRepository.readNotification(notificationId = notificationId)
        }
    }
}
