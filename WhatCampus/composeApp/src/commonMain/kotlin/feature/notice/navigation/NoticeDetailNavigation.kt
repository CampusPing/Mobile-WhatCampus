package feature.notice.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import core.common.extensions.navigateSingleTop
import core.model.Notice
import core.navigation.Route
import feature.notice.NoticeDetailScreen
import feature.notice.format
import feature.notice.noticeDatetimeFormatter
import feature.notice.parse
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
            "/${notice.datetime.format(formatter = noticeDatetimeFormatter).encodeBase64()}" +
            "/${notice.url.encodeBase64()}"
    navigateSingleTop(route = routeWithArgs)
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
        )
    ) { backStackEntry ->
        val noticeId = backStackEntry.arguments?.getLong(NOTICE_ID_ARGUMENT)
        val noticeTitle = backStackEntry.arguments?.getString(NOTICE_TITLE_ARGUMENT)
        val noticeDatetime = backStackEntry.arguments?.getString(NOTICE_DATETIME_ARGUMENT)
            ?.decodeBase64String()
            ?.parse(formatter = noticeDatetimeFormatter)
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
