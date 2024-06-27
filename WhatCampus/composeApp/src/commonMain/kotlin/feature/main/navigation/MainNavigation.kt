package feature.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.model.Notice
import core.navigation.Route
import feature.main.MainScreen

fun NavController.navigateMain() {
    navigate(Route.MainRoute.route) {
        val onboardingRoute = graph.startDestinationRoute

        onboardingRoute?.let {
            popUpTo(it) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}

fun NavGraphBuilder.mainNavGraph(
    onNoticeClick: (Notice) -> Unit,
) {
    composable(Route.MainRoute.route) {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            onNoticeClick = onNoticeClick,
        )
    }
}
