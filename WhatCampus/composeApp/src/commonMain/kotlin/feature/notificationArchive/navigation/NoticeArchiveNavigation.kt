package feature.notificationArchive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.model.Notification
import core.navigation.MainRoute
import feature.notificationArchive.NotificationArchiveScreen

fun NavController.navigateNotificationArchive() {
    navigateSingleTop(
        route = MainRoute.NotificationArchive,
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.notificationArchiveNavGraph(
    onClickBack: () -> Unit,
    onClickNewNoticeNotification: (Notification.NewNotice) -> Unit,
) {
    composable<MainRoute.NotificationArchive> {
        NotificationArchiveScreen(
            onClickBack = onClickBack,
            onClickNewNoticeNotification = onClickNewNoticeNotification,
        )
    }
}
