package feature.bookmark.components

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.common.util.defaultDateFormatter
import core.common.util.format
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.PaleGray
import core.designsystem.theme.WhatcamTheme
import core.model.Notice
import feature.bookmark.model.BookmarkUiState

@Composable
internal fun BookmarkList(
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
                BookmarkDivider()
            }
        }
    }
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
private fun BookmarkDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = horizontalPadding),
        thickness = 2.dp,
        color = PaleGray,
    )
}

private val horizontalPadding = 12.dp
