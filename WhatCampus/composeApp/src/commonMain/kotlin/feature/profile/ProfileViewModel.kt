package feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.UserRepository
import feature.profile.model.ProfileUiState
import feature.profile.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileUiState(
            user = User(
                userId = 1,
                universityId = 1,
                universityName = "상명대학교",
                departmentId = 1,
                departmentName = "컴퓨터과학과",
                fcmToken = "temp-12345",
                isPushNotificationAllowed = true,
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    // TODO: 이후에 Datastore에서 푸시 알림을 토글하는 기능을 추가할 예정입니다.
    fun changePushNotificationAllowed(isAllowed: Boolean) {
        _uiState.update { state ->
            state.copy(
                user = state.user.copy(
                    isPushNotificationAllowed = isAllowed,
                )
            )
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            userRepository.clearUser()
        }
    }
}
