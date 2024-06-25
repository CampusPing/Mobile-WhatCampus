package feature.notice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.di.koinViewModel
import feature.notice.components.NoticeTopAppBar

@Composable
fun NoticeScreen(
    noticeViewModel: NoticeViewModel = koinViewModel(),
) {
    Scaffold(
        topBar = {
            NoticeTopAppBar(
                onClickSearch = {},
                onClickNotification = {},
                onClickProfile = {},
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}
