package feature.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.model.Notice
import core.navigation.MainRoute
import feature.main.MainScreen

fun NavController.navigateMain(popUpTargetRoute: MainRoute?) {
    navigateSingleTop(
        route = MainRoute.HomeRoute,
        popUpTargetRoute = popUpTargetRoute,
        isPopUpToTargetRoute = true,
        isInclusive = true,
    )
}

fun NavGraphBuilder.mainNavGraph(
    onNoticeClick: (Notice) -> Unit,
    onClickNoticeSearch: () -> Unit,
    onClickNotificationArchive: () -> Unit,
    onClickProfile: () -> Unit,
) {
    composable<MainRoute.HomeRoute> {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            onNoticeClick = onNoticeClick,
            onClickNoticeSearch = onClickNoticeSearch,
            onClickNotificationArchive = onClickNotificationArchive,
            onClickProfile = onClickProfile,
        )
    }
}
