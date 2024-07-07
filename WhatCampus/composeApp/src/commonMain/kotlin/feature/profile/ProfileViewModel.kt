package feature.profile

import androidx.lifecycle.ViewModel
import feature.profile.model.ProfileUiState
import feature.profile.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileUiState(
            user = User(
                userId = 1,
                universityId = 1,
                universityName = "상명대학교",
                departmentId = 1,
                departmentName = "컴퓨터과학과",
                fcmToken = "temp-12345",
                isPushNotificationsAllowed = true,
            )
        )
    )
    val uiState = _uiState.asStateFlow()
}
