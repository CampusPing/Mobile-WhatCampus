package core.common.extensions

import androidx.navigation.NavController

fun NavController.navigateSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationRoute!!)
        launchSingleTop = true
    }
}
