package feature.profile.navigation

import core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed class ProfileRouteModel : Route() {
    @Serializable
    data object ProfileMain : ProfileRouteModel()

    @Serializable
    data object NoticeCategory : ProfileRouteModel()

    @Serializable
    data object Faq : ProfileRouteModel()

    @Serializable
    data object Privacy : ProfileRouteModel()
}
