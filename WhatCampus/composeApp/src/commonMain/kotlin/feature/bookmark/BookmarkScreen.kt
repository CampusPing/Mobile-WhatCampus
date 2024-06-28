package feature.bookmark

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import core.model.Notice
import feature.bookmark.components.BookmarkList
import feature.bookmark.components.BookmarkTopBar
import feature.bookmark.components.EmptyBookmarkScreen
import feature.bookmark.model.BookmarkUiState

@Composable
internal fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = koinViewModel(),
    onNoticeClick: (Notice) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    var isEditMode by rememberSaveable { mutableStateOf(false) }

    BookmarkScreen(
        modifier = modifier,
        onClickEdit = { isEditMode = true },
        onClickCancel = {
            isEditMode = false
            viewModel.clearMarkedNoticesForDelete()
        },
        onClickSelectAll = {
            if (uiState.isAllNoticesMarkedForDelete) {
                viewModel.clearMarkedNoticesForDelete()
            } else {
                viewModel.markAllNoticesForDelete()
            }
        },
        onClickUnbookmark = {
            isEditMode = false
            viewModel.unbookmarkNotices()
        },
        isEditMode = isEditMode,
        isAllSelected = uiState.isAllNoticesMarkedForDelete,
        onClickNotice = onNoticeClick,
        onClickNoticeForDelete = viewModel::markBookmarkForDelete,
        uiState = uiState,
    )
}

@Composable
private fun BookmarkScreen(
    modifier: Modifier = Modifier,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
    onClickSelectAll: () -> Unit,
    onClickUnbookmark: () -> Unit,
    isEditMode: Boolean,
    isAllSelected: Boolean,
    onClickNotice: (Notice) -> Unit,
    onClickNoticeForDelete: (Notice) -> Unit,
    uiState: BookmarkUiState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BookmarkTopBar(
                onClickEdit = onClickEdit,
                onClickCancel = onClickCancel,
                onClickSelectAll = onClickSelectAll,
                onClickUnbookmark = onClickUnbookmark,
                isEditMode = isEditMode,
                isAllSelected = isAllSelected,
                isShowActions = !uiState.isEmptyBookmark,
            )
        },
    ) { paddingValues ->
        if (uiState.isEmptyBookmark) {
            EmptyBookmarkScreen(modifier = Modifier.padding(paddingValues))
        } else {
            BookmarkList(
                paddingValues = paddingValues,
                uiState = uiState,
                onClickNotice = onClickNotice,
                onClickNoticeForDelete = onClickNoticeForDelete,
                isEditMode = isEditMode
            )
        }
    }
}
