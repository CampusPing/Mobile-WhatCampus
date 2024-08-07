package feature.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.model.Notice
import core.navigation.Route
import feature.main.MainScreen

fun NavController.navigateMain(popUpTargetRoute: Route?) {
    navigateSingleTop(
        route = Route.MainRoute.route,
        popUpTargetRoute = popUpTargetRoute?.route ?: graph.startDestinationRoute,
        isPopUpToTargetRoute = true,
        isInclusive = true,
    )
}

fun NavGraphBuilder.mainNavGraph(
    onNoticeClick: (Notice) -> Unit,
    onClickNoticeSearch: () -> Unit,
    onClickProfile: () -> Unit,
) {
    composable(Route.MainRoute.route) {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            onNoticeClick = onNoticeClick,
            onClickNoticeSearch = onClickNoticeSearch,
            onClickProfile = onClickProfile,
        )
    }
}
