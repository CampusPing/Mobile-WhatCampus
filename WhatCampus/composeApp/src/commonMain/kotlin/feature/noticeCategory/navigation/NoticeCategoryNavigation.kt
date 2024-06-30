package feature.noticeCategory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.noticeCategory.NoticeCategoryScreen

fun NavController.navigateToNoticeCategory() {
    navigateSingleTop(Route.NoticeCategory.route)
}

fun NavGraphBuilder.noticeCategoryNavGraph(
    onClickBack: () -> Unit,
) {
    composable(Route.NoticeCategory.route) {
        NoticeCategoryScreen(
            onClickBack = onClickBack,
        )
    }
}
