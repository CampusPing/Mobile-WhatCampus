package feature.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import core.navigation.MainRoute
import core.navigation.Route
import feature.main.MainTab
import feature.university.navigation.navigateDepartmentSelectivity
import feature.university.navigation.navigateUniversityComplete
import feature.university.navigation.navigateUniversitySelectivity

internal class WhatcamNavigator(
    val navController: NavHostController,
) {
    val startDestination: Route = Route.OnboardingRoute

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateUniversitySelectivity() {
        navController.navigateUniversitySelectivity()
    }

    fun navigateDepartmentSelectivity() {
        navController.navigateDepartmentSelectivity()
    }

    fun navigateUniversityComplete() {
        navController.navigateUniversityComplete()
    }

    fun navigateMain(tab: MainTab) {
        val navOptions = navOptions {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.NOTICE -> {}
            MainTab.BOOKMARK -> {}
            MainTab.CHAT -> {}
            MainTab.CAMPUS_MAP -> {}
        }
    }

    fun navigateUpIfNotNotice() {
        if (!isSameCurrentDestination(MainRoute.NOTICE)) {
            navigateUp()
        }
    }

    private inline fun isSameCurrentDestination(mainRoute: MainRoute): Boolean {
        return navController.currentDestination?.hasRoute(mainRoute.route, null) == true
    }
}

@Composable
internal fun rememberWhatcamNavigator(
    navController: NavHostController = rememberNavController(),
): WhatcamNavigator = remember(navController) {
    WhatcamNavigator(navController = navController)
}
