package feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import core.navigation.Route
import feature.profile.ProfileScreen

fun NavController.navigateProfile() {
    navigate(Route.Profile.route)
}

fun NavGraphBuilder.profileNavGraph(
    onClickBack: () -> Unit,
) {
    navigation(
        startDestination = ProfileRouteModel.ProfileMain.route,
        route = Route.Profile.route,
    ) {
        composable(ProfileRouteModel.ProfileMain.route) {
            ProfileScreen(
                onClickBack = onClickBack,
            )
        }
    }
}
