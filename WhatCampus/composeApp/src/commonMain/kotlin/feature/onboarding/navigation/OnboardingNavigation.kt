package feature.onboarding.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.navigation.MainRoute
import feature.onboarding.OnboardingScreen

fun NavController.navigateOnboarding(popUpTargetRoute: MainRoute? = null) {
    navigateSingleTop(
        route = MainRoute.OnboardingRoute,
        popUpTargetRoute = popUpTargetRoute,
        isPopUpToTargetRoute = true,
        isInclusive = true,
    )
}

fun NavGraphBuilder.onboardingNavGraph(
    onboardingComplete: () -> Unit,
) {
    composable<MainRoute.OnboardingRoute> {
        OnboardingScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 40.dp),
            onboardingComplete = onboardingComplete,
        )
    }
}
