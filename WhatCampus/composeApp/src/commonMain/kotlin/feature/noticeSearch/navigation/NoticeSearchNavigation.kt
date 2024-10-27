package feature.noticeSearch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.model.Notice
import core.navigation.MainRoute
import feature.noticeSearch.NoticeSearchScreen

fun NavController.navigateNoticeSearch() {
    navigate(MainRoute.NoticeSearch)
}

fun NavGraphBuilder.noticeSearchNavGraph(
    onClickBack: () -> Unit,
    onClickNotice: (Notice) -> Unit,
) {
    composable<MainRoute.NoticeSearch> {
        NoticeSearchScreen(
            onClickBack = onClickBack,
            onClickNotice = onClickNotice,
        )
    }
}
