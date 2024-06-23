package feature.onboarding.navigation

import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier,
    onboardingComplete: () -> Unit,
) {
    composable(Route.OnboardingRoute.route) {
        OnboardingScreen(
            modifier = modifier,
            onboardingComplete = onboardingComplete,
        )
    }
}
