package core.common.extensions

import androidx.navigation.NavController

fun NavController.navigateSingleTop(
    route: String,
    isPopUpToStartDestination: Boolean = true,
    isInclusive: Boolean = false,
) {
    navigate(route) {
        if (isPopUpToStartDestination) {
            val startRoute = graph.startDestinationRoute ?: return@navigate

            popUpTo(startRoute) {
                inclusive = isInclusive
            }
        }
        launchSingleTop = true
    }
}
