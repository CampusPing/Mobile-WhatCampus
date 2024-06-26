package feature.notice.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.notice.NoticeDetailScreen
import io.ktor.util.decodeBase64String
import io.ktor.util.encodeBase64

private const val NOTICE_ID_ARGUMENT = "noticeId"
private const val NOTICE_URL_ARGUMENT = "noticeUrl"

fun NavController.navigateNoticeDetail(
    noticeId: Long,
    noticeUrl: String,
) {
    val routeWithArgs = "${Route.NoticeDetail.route}/${noticeId}/${noticeUrl.encodeBase64()}"
    navigateSingleTop(route = routeWithArgs)
}

fun NavGraphBuilder.noticeDetailNavGraph() {
    composable(
        route = "${Route.NoticeDetail.route}/{$NOTICE_ID_ARGUMENT}/{$NOTICE_URL_ARGUMENT}",
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
        val noticeUrl = backStackEntry.arguments?.getString(NOTICE_URL_ARGUMENT)?.decodeBase64String()

        NoticeDetailScreen(
            modifier = Modifier.fillMaxSize(),
            noticeId = noticeId ?: 0,
            noticeUrl = noticeUrl ?: "https://www.google.com/",
        )
    }
}
