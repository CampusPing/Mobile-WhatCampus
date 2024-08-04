package feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.Route
import feature.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    onSplashDone: (shouldOnboarding: Boolean) -> Unit,
) {
    composable(Route.SplashRoute.route) {
        SplashScreen(
            onSplashDone = onSplashDone,
        )
    }
}
