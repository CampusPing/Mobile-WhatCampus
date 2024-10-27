package core.common.extensions

import androidx.navigation.NavController
import core.navigation.Route

fun NavController.navigateSingleTop(
    route: Route,
    popUpTargetRoute: Route? = null,
    isPopUpToTargetRoute: Boolean = true,
    isInclusive: Boolean = false,
) {
    navigate(route) {
        launchSingleTop = true

        if (isPopUpToTargetRoute) {
            popUpTargetRoute ?: return@navigate

            popUpTo(route = popUpTargetRoute) {
                inclusive = isInclusive
            }
        }
    }
}
