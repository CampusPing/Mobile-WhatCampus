package feature.notificationArchive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.NotificationRepository
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationArchiveViewModel(
    private val notificationRepository: NotificationRepository,
) : ViewModel() {

    val uiState = notificationRepository.flowNotifications()
        .map { notifications ->
            NotificationArchiveUiState(
                isLoading = false,
                notifications = notifications,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NotificationArchiveUiState(isLoading = true),
        )

    fun turnOffNewNotification() {
        viewModelScope.launch {
            notificationRepository.updateHasNewNotification(hasNewNotification = false)
        }
    }

    fun readNotification(notificationId: Long) {
        viewModelScope.launch {
            notificationRepository.readNotification(notificationId = notificationId)
        }
    }
}
