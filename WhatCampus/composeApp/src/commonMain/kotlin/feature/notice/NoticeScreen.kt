package feature.notice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import core.model.NoticeCategory
import feature.notice.components.NoticeCategoryList
import feature.notice.components.NoticeTopAppBar
import feature.notice.model.NoticeUiState

@Composable
fun NoticeScreen(
    noticeViewModel: NoticeViewModel = koinViewModel(),
) {
    val uiState by noticeViewModel.uiState.collectAsStateMultiplatform()

    when (uiState) {
        NoticeUiState.Loading -> {
            // TODO: 로딩 화면 구현
        }

        is NoticeUiState.Success -> NoticeScreen(
            uiState = uiState as NoticeUiState.Success,
            onClickCategory = { noticeViewModel.selectCategory(it) },
        )
    }


}

@Composable
private fun NoticeScreen(
    uiState: NoticeUiState.Success,
    onClickCategory: (NoticeCategory) -> Unit,
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
            NoticeCategoryList(
                modifier = Modifier.padding(top = 8.dp),
                noticeCategories = uiState.noticeCategories,
                selectedCategory = uiState.selectedCategory,
                onClickCategory = onClickCategory,
            )
        }
    }
}
