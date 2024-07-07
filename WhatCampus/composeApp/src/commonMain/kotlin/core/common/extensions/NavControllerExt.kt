package core.common.extensions

import androidx.navigation.NavController

fun NavController.navigateSingleTop(
    route: String,
    isPopUpToStartDestination: Boolean = true,
    inclusiveStartDestination: Boolean = false,
) {
    navigate(route) {
        val startDestination = graph.startDestinationRoute

        if (isPopUpToStartDestination) {
            startDestination?.let {
                popUpTo(startDestination) {
                    inclusive = inclusiveStartDestination
                }
            }
        }
        launchSingleTop = true
    }
}
