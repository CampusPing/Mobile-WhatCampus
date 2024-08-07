package core.common.extensions

import androidx.navigation.NavController

fun NavController.navigateSingleTop(
    route: String,
    popUpTargetRoute: String? = graph.startDestinationRoute,
    isPopUpToTargetRoute: Boolean = true,
    isInclusive: Boolean = false,
) {
    navigate(route) {
        if (isPopUpToTargetRoute) {
            popUpTargetRoute ?: return@navigate

            popUpTo(route = popUpTargetRoute) {
                inclusive = isInclusive
            }
        }
        launchSingleTop = true
    }
}
