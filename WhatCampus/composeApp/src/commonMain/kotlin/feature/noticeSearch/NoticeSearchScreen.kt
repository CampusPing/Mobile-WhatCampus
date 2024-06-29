package feature.noticeSearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import core.model.Notice
import feature.noticeSearch.components.NoticeList
import feature.noticeSearch.components.NoticeSearchTopBar
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

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NoticeSearchTopBar(onClickBack = onClickBack) }
    ) { paddingValues ->
        when (val state = uiState) {
            NoticeSearchUiState.Loading -> LoadingScreen(
                modifier = Modifier.padding(paddingValues)
            )

            is NoticeSearchUiState.Success -> NoticeSearchScreen(
                modifier = Modifier.padding(paddingValues),
                onClickNotice = onClickNotice,
                uiState = state,
                query = searchQuery,
                onQueryChange = viewModel::searchNotice,
            )
        }
    }
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NoticeSearchScreen(
    modifier: Modifier = Modifier,
    onClickNotice: (Notice) -> Unit,
    uiState: NoticeSearchUiState.Success,
    query: String,
    onQueryChange: (String) -> Unit,
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

        NoticeList(
            notices = uiState.searchedNotices,
            onClickNotice = onClickNotice,
        )
    }
}
