package feature.notice.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import core.common.extensions.navigateSingleTop
import core.model.Notice
import core.navigation.MainRoute
import feature.notice.NoticeDetailScreen
import kotlin.reflect.typeOf

fun NavController.navigateNoticeDetail(notice: Notice) {
    navigateSingleTop(
        route = MainRoute.NoticeDetail(notice),
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.noticeDetailNavGraph(
    onClickBack: () -> Unit,
) {
    composable<MainRoute.NoticeDetail>(
        enterTransition = { slidingStartEnterTransition() },
        exitTransition = { slidingEndOutTransition() },
        typeMap = mapOf(typeOf<Notice>() to NoticeNavType),
    ) { backStackEntry ->
        val noticeDetailRoute = backStackEntry.toRoute<MainRoute.NoticeDetail>()

        NoticeDetailScreen(
            modifier = Modifier.fillMaxSize(),
            notice = noticeDetailRoute.notice,
            onClickBack = onClickBack,
        )
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.slidingStartEnterTransition() = slideIntoContainer(
    animationSpec = tween(300, easing = EaseIn),
    towards = AnimatedContentTransitionScope.SlideDirection.Start
)

private fun AnimatedContentTransitionScope<NavBackStackEntry>.slidingEndOutTransition() = slideOutOfContainer(
    animationSpec = tween(300, easing = EaseOut),
    towards = AnimatedContentTransitionScope.SlideDirection.End
)
