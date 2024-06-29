package core.common.extensions

import androidx.navigation.NavController

fun NavController.navigateSingleTop(
    route: String,
    isPopUpToStartDestination: Boolean = true,
) {
    navigate(route) {
        if (isPopUpToStartDestination) {
            popUpTo(graph.startDestinationRoute!!)
        }
        launchSingleTop = true
    }
}
