package feature.noticeSearch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.components.LoadingScreen
import core.designsystem.components.dialog.MutableDialogState
import core.designsystem.components.dialog.rememberDialogState
import core.di.koinViewModel
import core.model.Notice
import feature.noticeSearch.components.EmptyNoticeSearchScreen
import feature.noticeSearch.components.NoticeList
import feature.noticeSearch.components.NoticeSearchDeleteDialog
import feature.noticeSearch.components.NoticeSearchTopBar
import feature.noticeSearch.components.RecentSearchHistory
import feature.noticeSearch.model.NoticeSearchClearType
import feature.noticeSearch.model.NoticeSearchUiState
import feature.university.components.SearchBar
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_search_hint

@Composable
internal fun NoticeSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: NoticeSearchViewModel = koinViewModel(),
    onClickBack: () -> Unit,
    onClickNotice: (Notice) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    val searchQuery by viewModel.noticeSearchQuery.collectAsStateMultiplatform()
    val dialogState: MutableDialogState<NoticeSearchClearType> = rememberDialogState(NoticeSearchClearType.Nothing)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NoticeSearchTopBar(onClickBack = onClickBack) }
    ) { paddingValues ->
        if (uiState.isLoading) {
            LoadingScreen(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }

        NoticeSearchScreen(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            onClickNotice = { notice ->
                viewModel.addSearchHistory()
                onClickNotice(notice)
            },
            query = searchQuery,
            onQueryChange = viewModel::searchNotice,
            onClickDeleteHistory = { query -> dialogState.showDialog(NoticeSearchClearType.Delete(query)) },
            onClickDeleteAllHistory = { dialogState.showDialog(NoticeSearchClearType.DeleteAll) },
        )

        if (dialogState.isVisible.value) {
            NoticeSearchDeleteDialog(
                dialogState = dialogState,
                onClickDeleteHistory = { query ->
                    viewModel.deleteSearchHistory(query = query)
                    dialogState.hideDialog()
                },
                onClickDeleteAllHistory = {
                    viewModel.clearSearchHistories()
                    dialogState.hideDialog()
                },
            )
        }
    }
}

@Composable
private fun NoticeSearchScreen(
    modifier: Modifier = Modifier,
    uiState: NoticeSearchUiState,
    onClickNotice: (Notice) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onClickDeleteHistory: (String) -> Unit,
    onClickDeleteAllHistory: () -> Unit,
) {

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 8.dp),
            value = query,
            onValueChange = onQueryChange,
            hint = stringResource(Res.string.notice_search_hint),
        )

        AnimatedVisibility(!uiState.isEmptyHistory) {
            RecentSearchHistory(
                searchHistory = uiState.searchHistories,
                onClickSearchHistory = onQueryChange,
                onClickDeleteHistory = onClickDeleteHistory,
                onClickDeleteAllHistory = onClickDeleteAllHistory,
            )
        }

        AnimatedVisibility(uiState.isEmptyResult) {
            EmptyNoticeSearchScreen()
        }

        AnimatedVisibility(!uiState.isEmptyResult) {
            NoticeList(
                notices = uiState.searchedNotices,
                onClickNotice = onClickNotice,
            )
        }
    }
}
