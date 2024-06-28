package feature.bookmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.common.util.defaultDateFormatter
import core.common.util.format
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.PaleGray
import core.designsystem.theme.WhatcamTheme
import core.di.koinViewModel
import core.model.Notice
import feature.bookmark.model.BookmarkUiState
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.bookmark_cancel
import whatcampus.composeapp.generated.resources.bookmark_edit
import whatcampus.composeapp.generated.resources.bookmark_title
import whatcampus.composeapp.generated.resources.unbookmark

private val horizontalPadding = 12.dp

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
            EmptyBookmarkScreen()
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

@Composable
private fun BookmarkList(
    paddingValues: PaddingValues,
    uiState: BookmarkUiState,
    onClickNotice: (Notice) -> Unit,
    onClickNoticeForDelete: (Notice) -> Unit,
    isEditMode: Boolean,
) {
    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = uiState.notices,
            key = { _, notice -> notice.id },
        ) { index, notice ->
            BookmarkItem(
                notice = notice,
                onClick = { onClickNotice(notice) },
                onClickDelete = onClickNoticeForDelete,
                isSelectedForDelete = notice in uiState.markedNoticesForDelete,
                isEditMode = isEditMode,
            )

            if (index < uiState.notices.size - 1) {
                NoticeDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookmarkTopBar(
    modifier: Modifier = Modifier,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
    onClickSelectAll: () -> Unit,
    onClickUnbookmark: () -> Unit,
    isEditMode: Boolean,
    isAllSelected: Boolean,
    isShowActions: Boolean,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.bookmark_title),
                style = WhatcamTheme.typography.titleMediumB,
                color = Graphite,
            )
        },
        navigationIcon = {
            if (isEditMode) {
                IconButton(onClick = onClickCancel) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(Res.string.bookmark_cancel),
                        tint = Graphite,
                    )
                }
            }
        },
        actions = {
            if (isShowActions) {
                if (isEditMode) {
                    RadioButton(
                        selected = isAllSelected,
                        onClick = onClickSelectAll,
                    )

                    IconButton(onClick = onClickUnbookmark) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(Res.string.unbookmark),
                            tint = Graphite,
                        )
                    }
                } else {
                    IconButton(onClick = onClickEdit) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(Res.string.bookmark_edit),
                            tint = Graphite,
                        )
                    }
                }
            }
        },
    )
}

@Composable
private fun BookmarkItem(
    modifier: Modifier = Modifier,
    notice: Notice,
    onClick: () -> Unit,
    onClickDelete: (Notice) -> Unit,
    isSelectedForDelete: Boolean,
    isEditMode: Boolean,
) {
    Row {
        Column(
            modifier = modifier
                .clickable(
                    onClick = onClick,
                    enabled = !isEditMode,
                )
                .weight(1f)
                .padding(horizontal = horizontalPadding, vertical = 16.dp)
                .heightIn(min = 48.dp, max = 80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = notice.title,
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = notice.datetime.format(formatter = defaultDateFormatter),
                style = WhatcamTheme.typography.bodyMediumR,
                color = Gray,
            )
        }

        if (isEditMode) {
            RadioButton(
                selected = isSelectedForDelete,
                onClick = { onClickDelete(notice) },
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
            )
        }
    }
}

@Composable
private fun NoticeDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = horizontalPadding),
        thickness = 2.dp,
        color = PaleGray,
    )
}

@Composable
private fun EmptyBookmarkScreen(
    modifier: Modifier = Modifier,
) {

}
