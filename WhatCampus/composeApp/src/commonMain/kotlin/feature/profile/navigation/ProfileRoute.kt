package feature.profile.navigation

import core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed class ProfileRoute : Route() {
    @Serializable
    data object ProfileMain : ProfileRoute()

    @Serializable
    data object NoticeCategory : ProfileRoute()

    @Serializable
    data object Faq : ProfileRoute()

    @Serializable
    data object Privacy : ProfileRoute()
}
