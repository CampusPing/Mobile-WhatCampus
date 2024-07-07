package feature.profile.model

data class ProfileUiState(
    val user: User,
)

// TODO: 이후에 유저 정보를 저장하는 기능이 추가되면 제거될 클래스입니다.
data class User(
    val userId: Long,
    val universityId: Long,
    val universityName: String,
    val departmentId: Long,
    val departmentName: String,
    val fcmToken: String,
    val isPushNotificationAllowed: Boolean,
)
