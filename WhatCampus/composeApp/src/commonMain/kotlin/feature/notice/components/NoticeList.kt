package feature.notice.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.PaleGray
import core.designsystem.theme.WhatcamTheme
import core.model.Notice
import feature.notice.format
import feature.notice.noticeDateFormatter

private val horizontalPadding = 12.dp

@Composable
internal fun NoticeList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    notices: List<Notice>,
    onClickItem: (Notice) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 56.dp),
        state = listState,
    ) {
        itemsIndexed(
            items = notices,
            key = { _, notice -> notice.id },
        ) { index, notice ->
            NoticeItem(
                notice = notice,
                onClick = { onClickItem(notice) },
            )

            if (index < notices.size - 1) {
                NoticeDivider()
            }
        }
    }
}

@Composable
private fun NoticeItem(
    modifier: Modifier = Modifier,
    notice: Notice,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
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
            text = notice.datetime.format(formatter = noticeDateFormatter),
            style = WhatcamTheme.typography.bodyMediumR,
            color = Gray,
        )
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
