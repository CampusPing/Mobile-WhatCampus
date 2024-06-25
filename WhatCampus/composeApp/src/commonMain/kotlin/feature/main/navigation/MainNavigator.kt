package feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import feature.main.MainTab

internal class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination: MainRoute = MainRoute.NOTICE

    internal fun NavController.navigate(
        mainTab: MainTab,
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

        when (mainTab.route) {
            MainRoute.NOTICE -> navigate(MainRoute.NOTICE.route, navOptions)
            MainRoute.BOOKMARK -> navigate(MainRoute.BOOKMARK.route, navOptions)
            MainRoute.CHAT -> navigate(MainRoute.CHAT.route, navOptions)
            MainRoute.CAMPUS_MAP -> navigate(MainRoute.CAMPUS_MAP.route, navOptions)
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController = navController)
}
