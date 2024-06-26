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

private const val NOTICE_URL_ARGUMENT = "noticeUrl"

fun NavController.navigateNoticeDetail(noticeUrl: String) {
    navigateSingleTop("${Route.NoticeDetail.route}/${noticeUrl.encodeBase64()}")
}

fun NavGraphBuilder.noticeDetailNavGraph() {
    composable(
        route = "${Route.NoticeDetail.route}/{$NOTICE_URL_ARGUMENT}",
        arguments = listOf(
            navArgument(NOTICE_URL_ARGUMENT) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val noticeUrl = backStackEntry.arguments?.getString("noticeUrl")?.decodeBase64String()

        NoticeDetailScreen(
            modifier = Modifier.fillMaxSize(),
            noticeUrl = noticeUrl ?: "https://www.google.com/",
        )
    }
}
