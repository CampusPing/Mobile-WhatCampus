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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import core.common.extensions.navigateSingleTop
import core.common.util.defaultDatetimeFormatter
import core.common.util.format
import core.common.util.parse
import core.model.Notice
import core.navigation.Route
import feature.notice.NoticeDetailScreen
import io.ktor.util.decodeBase64String
import io.ktor.util.encodeBase64
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime

private const val NOTICE_ID_ARGUMENT = "noticeId"
private const val NOTICE_TITLE_ARGUMENT = "noticeTitle"
private const val NOTICE_DATETIME_ARGUMENT = "noticeDateTime"
private const val NOTICE_URL_ARGUMENT = "noticeUrl"

fun NavController.navigateNoticeDetail(notice: Notice) {
    val routeWithArgs = Route.NoticeDetail.route +
            "/${notice.id}" +
            "/${notice.title}" +
            "/${notice.datetime.format(formatter = defaultDatetimeFormatter).encodeBase64()}" +
            "/${notice.url.encodeBase64()}"

    navigateSingleTop(
        route = routeWithArgs,
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.noticeDetailNavGraph(
    onClickBack: () -> Unit,
) {
    composable(
        route = Route.NoticeDetail.route +
                "/{$NOTICE_ID_ARGUMENT}" +
                "/{$NOTICE_TITLE_ARGUMENT}" +
                "/{$NOTICE_DATETIME_ARGUMENT}" +
                "/{$NOTICE_URL_ARGUMENT}",
        arguments = listOf(
            navArgument(NOTICE_ID_ARGUMENT) {
                type = NavType.LongType
                nullable = false
            },
            navArgument(NOTICE_URL_ARGUMENT) {
                type = NavType.StringType
                nullable = false
            }
        ),
        enterTransition = { slidingStartEnterTransition() },
        exitTransition = { slidingEndOutTransition() },
    ) { backStackEntry ->
        val noticeId = backStackEntry.arguments?.getLong(NOTICE_ID_ARGUMENT)
        val noticeTitle = backStackEntry.arguments?.getString(NOTICE_TITLE_ARGUMENT)
        val noticeDatetime = backStackEntry.arguments?.getString(NOTICE_DATETIME_ARGUMENT)
            ?.decodeBase64String()
            ?.parse(formatter = defaultDatetimeFormatter)
        val noticeUrl = backStackEntry.arguments?.getString(NOTICE_URL_ARGUMENT)?.decodeBase64String()
        val notice = Notice(
            id = noticeId ?: 0,
            title = noticeTitle.orEmpty(),
            datetime = noticeDatetime ?: Clock.System.now().toLocalDateTime(currentSystemDefault()),
            url = noticeUrl.orEmpty(),
        )

        NoticeDetailScreen(
            modifier = Modifier.fillMaxSize(),
            notice = notice,
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
