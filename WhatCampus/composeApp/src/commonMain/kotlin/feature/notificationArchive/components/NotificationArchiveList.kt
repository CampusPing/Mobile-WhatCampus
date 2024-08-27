package feature.notificationArchive.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.common.util.defaultDateFormatter
import core.common.util.format
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.PaleGray
import core.designsystem.theme.QuietGray
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.model.NotificationArchive
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun NotificationArchiveList(
    modifier: Modifier = Modifier,
    notificationArchives: PersistentList<NotificationArchive>,
    onClickNewNoticeNotification: (NotificationArchive.NewNotice) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = notificationArchives,
            key = { _, notificationArchive -> notificationArchive.id }
        ) { index, notificationArchive ->
            when (notificationArchive) {
                is NotificationArchive.NewNotice -> NewNoticeItem(
                    newNotice = notificationArchive,
                    onClick = { onClickNewNoticeNotification(notificationArchive) },
                )
            }

            if (index < notificationArchives.size - 1) {
                NotificationArchiveDivider()
            }
        }
    }
}

@Composable
private fun NewNoticeItem(
    modifier: Modifier = Modifier,
    newNotice: NotificationArchive.NewNotice,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(color = if (newNotice.isRead) QuietGray else White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = horizontalPadding, vertical = 16.dp)
            .heightIn(min = 48.dp, max = 72.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = newNotice.content,
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = newNotice.receivedDatetime.format(formatter = defaultDateFormatter),
                style = WhatcamTheme.typography.bodyMediumR,
                color = Gray,
            )
        }
    }
}

@Composable
private fun NotificationArchiveDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = horizontalPadding),
        thickness = 2.dp,
        color = PaleGray,
    )
}

private val horizontalPadding = 12.dp
