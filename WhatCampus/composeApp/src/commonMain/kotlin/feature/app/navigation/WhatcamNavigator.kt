package feature.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import core.navigation.Route
import feature.university.navigation.navigateUniversitySelectivity

internal class WhatcamNavigator(
    val navController: NavHostController,
) {
    val startDestination: Route = Route.OnboardingRoute

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateUniversitySelectivity() {
        navController.navigateUniversitySelectivity()
    }
}

@Composable
internal fun rememberWhatcamNavigator(
    navController: NavHostController = rememberNavController(),
): WhatcamNavigator = remember(navController) {
    WhatcamNavigator(navController = navController)
}
