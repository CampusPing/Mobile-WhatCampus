package feature.notificationArchive

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.common.extensions.collectUiEvent
import core.common.util.logScreenEvent
import core.designsystem.components.LoadingScreen
import core.di.koinViewModel
import core.model.Notification
import feature.notificationArchive.components.NotificationArchiveList
import feature.notificationArchive.components.NotificationArchiveTopBar

@Composable
fun NotificationArchiveScreen(
    modifier: Modifier = Modifier,
    viewModel: NotificationArchiveViewModel = koinViewModel(),
    onClickBack: () -> Unit,
    onClickNewNoticeNotification: (Notification.NewNotice) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    viewModel.commonUiEvent.collectUiEvent()

    logScreenEvent(screenName = "NotificationArchiveScreen")

    LaunchedEffect(Unit) {
        viewModel.turnOffNewNotification()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NotificationArchiveTopBar(onClickBack = onClickBack) }
    ) { paddingValues ->
        if (uiState.isLoading) {
            LoadingScreen(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }

        NotificationArchiveList(
            modifier = modifier.padding(paddingValues),
            notifications = uiState.notifications,
            onClickNewNoticeNotification = { newNoticeNotification ->
                viewModel.readNotification(notificationId = newNoticeNotification.id)
                onClickNewNoticeNotification(newNoticeNotification)
            },
        )
    }
}
