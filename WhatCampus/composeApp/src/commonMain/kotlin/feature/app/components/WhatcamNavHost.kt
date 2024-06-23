package feature.app.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import feature.app.navigation.WhatcamNavigator
import feature.onboarding.navigation.onboardingNavGraph
import feature.university.navigation.universityNavGraph

@Composable
internal fun WhatcamNavHost(
    navigator: WhatcamNavigator,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination.route,
    ) {
        onboardingNavGraph(
            onboardingComplete = { navigator.navigateUniversitySelectivity() }
        )
        universityNavGraph(

        )
    }
}
