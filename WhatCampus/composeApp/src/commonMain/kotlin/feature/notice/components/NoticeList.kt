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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.WhatcamTheme
import core.model.Notice
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@Composable
internal fun NoticeList(
    modifier: Modifier = Modifier,
    notices: List<Notice>,
    onClickItem: (Notice) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 56.dp),
    ) {
        items(
            items = notices,
            key = { notice -> notice.id },
        ) { notice ->
            NoticeItem(
                notice = notice,
                onClick = { onClickItem(notice) },
            )
        }
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
private val noticeDatetimeFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy/MM/dd")
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
            .padding(horizontal = 12.dp, vertical = 16.dp)
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
            text = notice.datetime.format(noticeDatetimeFormatter),
            style = WhatcamTheme.typography.bodyMediumR,
            color = Gray,
        )
    }
}
