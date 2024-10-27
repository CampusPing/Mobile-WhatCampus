package feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.MainRoute
import feature.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    onSplashDone: (shouldOnboarding: Boolean) -> Unit,
) {
    composable<MainRoute.SplashRoute> {
        SplashScreen(
            onSplashDone = onSplashDone,
        )
    }
}
