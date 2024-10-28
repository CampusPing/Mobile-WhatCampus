package core.navigation

import core.model.Notice
import kotlinx.serialization.Serializable

@Serializable
open class Route

@Serializable
sealed class MainRoute : Route() {
    @Serializable
    data object SplashRoute : MainRoute()

    @Serializable
    data object OnboardingRoute : MainRoute()

    @Serializable
    data object UniversityRoute : MainRoute()

    @Serializable
    data object HomeRoute : MainRoute()

    @Serializable
    data class NoticeDetail(val notice: Notice) : MainRoute()

    @Serializable
    data object NoticeSearch : MainRoute()

    @Serializable
    data object NotificationArchive : MainRoute()

    @Serializable
    data object Profile : MainRoute()
}
