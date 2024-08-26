package feature.notificationArchive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.notificationArchive.NotificationArchiveScreen

fun NavController.navigateNotificationArchive() {
    navigateSingleTop(
        route = Route.NotificationArchive.route,
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.notificationArchiveNavGraph(
    onClickBack: () -> Unit,
) {
    composable(Route.NotificationArchive.route) {
        NotificationArchiveScreen(
            onClickBack = onClickBack,
        )
    }
}
