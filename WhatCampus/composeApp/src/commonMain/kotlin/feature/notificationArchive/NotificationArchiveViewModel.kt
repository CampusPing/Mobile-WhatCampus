package feature.notificationArchive

import androidx.lifecycle.viewModelScope
import core.common.CommonViewModel
import core.domain.repository.NotificationRepository
import core.domain.repository.UserRepository
import core.model.Notification
import core.model.Response
import feature.notificationArchive.model.NotificationArchiveUiState
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationArchiveViewModel(
    private val userRepository: UserRepository,
    private val notificationRepository: NotificationRepository,
) : CommonViewModel() {

    private val _uiState = MutableStateFlow(NotificationArchiveUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchNotifications()
        }
    }

    fun turnOffNewNotification() {
        viewModelScope.launch {
            notificationRepository.updateHasNewNotification(hasNewNotification = false)
        }
    }

    fun readNotification(notificationId: Long) {
        viewModelScope.launch {
            val user = userRepository.flowUser().firstOrNull() ?: return@launch sendOtherErrorEvent()

            notificationRepository.readNotification(
                userId = user.userId,
                notificationId = notificationId,
            )
            fetchNotifications()
        }
    }

    private suspend fun fetchNotifications() {
        val user = userRepository.flowUser().firstOrNull() ?: return sendOtherErrorEvent()
        notificationRepository.flowNotifications(userId = user.userId).collect(::handleResponse)
    }

    private suspend fun handleResponse(response: Response<PersistentList<Notification>>) {
        when (response) {
            is Response.Success -> _uiState.update { it.copy(isLoading = false, notifications = response.body) }
            Response.Failure.ClientError -> sendClientErrorEvent()
            Response.Failure.ServerError -> sendServerErrorEvent()
            Response.Failure.NetworkError -> sendNetworkErrorEvent()
            is Response.Failure.OtherError<*> -> sendOtherErrorEvent()
        }
    }
}
