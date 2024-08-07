package feature.profile.model

import core.model.User

data class ProfileUiState(
    val user: User,
) {
    companion object {
        val EMPTY_STATE = ProfileUiState(
            user = User(
                userId = -1L,
                universityId = -1L,
                universityName = "-",
                departmentId = -1L,
                departmentName = "-",
                fcmToken = "invalid",
                isPushNotificationAllowed = false,
            )
        )
    }
}
