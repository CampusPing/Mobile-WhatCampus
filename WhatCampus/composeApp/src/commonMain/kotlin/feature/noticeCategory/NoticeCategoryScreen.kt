package feature.noticeCategory

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.components.NoticeCategoryList
import core.di.koinViewModel
import feature.noticeCategory.components.NoticeCategoryTopBar
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_category_saved_action_label
import whatcampus.composeapp.generated.resources.notice_category_saved_message

@Composable
internal fun NoticeCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: NoticeCategoryViewModel = koinViewModel(),
    onClickBack: () -> Unit,
    onClickSave: (savedMessage: String, actionLabel: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    val savedMessage = stringResource(Res.string.notice_category_saved_message)
    val savedActionLabel = stringResource(Res.string.notice_category_saved_action_label)

    Scaffold(
        modifier = modifier,
        topBar = {
            NoticeCategoryTopBar(
                onClickBack = onClickBack,
                onClickSave = {
                    viewModel.subscribeNoticeCategories()
                    onClickSave(savedMessage, savedActionLabel)
                },
            )
        }
    ) { paddingValues ->
        NoticeCategoryList(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp),
            noticeCategories = uiState.noticeCategories,
            subscribedNoticeCategories = uiState.subscribedNoticeCategories,
            onClickCategory = viewModel::toggleNoticeCategory,
        )
    }
}
