package feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

internal class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination: MainRoute = MainRoute.NOTICE

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentRoute: MainRoute
        @Composable get() = currentDestination?.route?.let(MainRoute::of) ?: startDestination

    internal fun navigate(
        mainRoute: MainRoute,
    ) {
        val navOptions = navOptions {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (mainRoute) {
            MainRoute.NOTICE -> navController.navigate(MainRoute.NOTICE.route, navOptions)
            MainRoute.BOOKMARK -> navController.navigate(MainRoute.BOOKMARK.route, navOptions)
            MainRoute.CHAT -> navController.navigate(MainRoute.CHAT.route, navOptions)
            MainRoute.CAMPUS_MAP -> navController.navigate(MainRoute.CAMPUS_MAP.route, navOptions)
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController = navController)
}
