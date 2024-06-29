package feature.noticeSearch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.model.Notice
import core.navigation.Route
import feature.noticeSearch.NoticeSearchScreen

fun NavController.navigateNoticeSearch() {
    navigate(Route.NoticeSearch.route)
}

fun NavGraphBuilder.noticeSearchNavGraph(
    onClickBack: () -> Unit,
    onClickNotice: (Notice) -> Unit,
) {
    composable(Route.NoticeSearch.route) {
        NoticeSearchScreen(
            onClickBack = onClickBack,
            onClickNotice = onClickNotice,
        )
    }
}
