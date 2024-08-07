package feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.UserRepository
import feature.profile.model.ProfileUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = userRepository.flowUser()
        .filterNotNull()
        .map { user -> ProfileUiState(user) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProfileUiState.EMPTY_STATE,
        )

    fun changePushNotificationAllowed(isAllowed: Boolean) {
        viewModelScope.launch {
            val user = uiState.value.user
            userRepository.updateUser(user.copy(isPushNotificationAllowed = isAllowed))
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            userRepository.clearUser()
        }
    }
}
