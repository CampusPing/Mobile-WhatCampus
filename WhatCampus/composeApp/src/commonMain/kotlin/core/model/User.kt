package core.model

data class User(
    val userId: Long,
    val universityId: Long,
    val universityName: String,
    val departmentId: Long,
    val departmentName: String,
    val fcmToken: String,
    val isPushNotificationAllowed: Boolean,
)
