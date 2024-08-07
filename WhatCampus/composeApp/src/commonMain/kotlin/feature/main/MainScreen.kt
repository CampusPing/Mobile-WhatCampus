package feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import core.common.extensions.provideOrRequestNotificationPermission
import core.model.Notice
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import feature.bookmark.BookmarkScreen
import feature.campusmap.CampusMapScreen
import feature.chat.ChatScreen
import feature.main.components.MainBottomAppBar
import feature.main.navigation.MainRoute
import feature.main.navigation.rememberMainNavigator
import feature.notice.NoticeScreen

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    onNoticeClick: (Notice) -> Unit,
    onClickNoticeSearch: () -> Unit,
    onClickProfile: () -> Unit,
) {
    val navigator = rememberMainNavigator()

    val permissionControllerFactory = rememberPermissionsControllerFactory()
    val permissionController = remember(permissionControllerFactory) {
        permissionControllerFactory.createPermissionsController()
    }

    BindEffect(permissionController)

    LaunchedEffect(Unit) {
        permissionController.provideOrRequestNotificationPermission()
    }

    Scaffold(
        modifier = modifier,
        bottomBar = { MainBottomAppBar(navigator = navigator) },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navigator.navController,
            startDestination = navigator.startDestination.route,
        ) {
            composable(MainRoute.NOTICE.route) {
                NoticeScreen(
                    onNoticeClick = onNoticeClick,
                    onClickNoticeSearch = onClickNoticeSearch,
                    onClickProfile = onClickProfile,
                )
            }
            composable(MainRoute.BOOKMARK.route) {
                BookmarkScreen(
                    onNoticeClick = onNoticeClick,
                )
            }
            composable(MainRoute.CHAT.route) {
                ChatScreen()
            }
            composable(MainRoute.CAMPUS_MAP.route) {
                CampusMapScreen()
            }
        }
    }
}
