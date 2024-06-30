package feature.noticeCategory

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import feature.noticeCategory.components.NoticeCategoryList
import feature.noticeCategory.components.NoticeCategoryTopBar

@Composable
internal fun NoticeCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: NoticeCategoryViewModel = koinViewModel(),
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

    Scaffold(
        modifier = modifier,
        topBar = {
            NoticeCategoryTopBar(
                onClickBack = onClickBack,
                onClickSave = {},
            )
        }
    ) { paddingValues ->
        NoticeCategoryList(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp),
            noticeCategories = uiState.noticeCategoryList,
            selectedNoticeCategories = uiState.selectedNoticeCategories,
            onClickCategory = viewModel::toggleNoticeCategory,
        )
    }
}

