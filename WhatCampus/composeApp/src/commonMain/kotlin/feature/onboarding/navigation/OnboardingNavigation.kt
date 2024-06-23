package feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.onboarding.OnboardingScreen

fun NavController.navigateOnboarding() {
    navigateSingleTop(Route.OnboardingRoute.route)
}

fun NavGraphBuilder.onboardingNavGraph(
    onboardingComplete: () -> Unit,
) {
    composable(Route.OnboardingRoute.route) {
        OnboardingScreen(
            onboardingComplete = onboardingComplete,
        )
    }
}
