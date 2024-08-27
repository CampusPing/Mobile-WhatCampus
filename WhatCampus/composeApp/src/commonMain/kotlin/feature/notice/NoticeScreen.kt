package feature.notice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import core.model.Notice
import feature.notice.components.NoticeCategoryBar
import feature.notice.components.NoticeList
import feature.notice.components.NoticeTopAppBar

@Composable
fun NoticeScreen(
    noticeViewModel: NoticeViewModel = koinViewModel(),
    onNoticeClick: (Notice) -> Unit,
    onClickNoticeSearch: () -> Unit,
    onClickNotificationArchive: () -> Unit,
    onClickProfile: () -> Unit,
) {
    val uiState by noticeViewModel.uiState.collectAsStateMultiplatform()

    Scaffold(
        topBar = {
            NoticeTopAppBar(
                hasNewNotification = uiState.hasNewNotification,
                onClickSearch = onClickNoticeSearch,
                onClickNotificationArchive = onClickNotificationArchive,
                onClickProfile = onClickProfile,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            val noticeListScrollState = rememberLazyListState()

            val isAtTop by remember {
                derivedStateOf {
                    noticeListScrollState.firstVisibleItemIndex < 2
                }
            }

            NoticeList(
                listState = noticeListScrollState,
                noticesWithBookmark = uiState.notices,
                onClickItem = onNoticeClick,
            )

            AnimatedVisibility(
                visible = isAtTop,
                enter = fadeIn() + slideInVertically { height -> -height },
                exit = fadeOut() + slideOutVertically { height -> -height }
            ) {
                NoticeCategoryBar(
                    noticeCategories = uiState.noticeCategories,
                    selectedCategory = uiState.selectedCategory,
                    onClickCategory = noticeViewModel::selectCategory,
                )
            }
        }
    }
}
