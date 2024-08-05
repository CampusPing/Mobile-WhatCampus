package feature.noticeSearch.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.DarkGray
import core.designsystem.theme.Gray
import core.designsystem.theme.Mint01A70
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_search_clear
import whatcampus.composeapp.generated.resources.notice_search_history

@Composable
internal fun RecentSearchHistory(
    modifier: Modifier = Modifier,
    searchHistory: List<String> = emptyList(),
    onClickSearchHistory: (query: String) -> Unit,
    onClickDeleteHistory: (query: String) -> Unit,
    onClickClear: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RecentSearchHistoryHeader(onClickClear = onClickClear)

        SearchHistoryList(
            searchHistories = searchHistory,
            onClickSearchHistory = onClickSearchHistory,
            onClickDeleteHistory = onClickDeleteHistory
        )
    }
}

@Composable
private fun RecentSearchHistoryHeader(
    onClickClear: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.notice_search_history),
            color = DarkGray,
            style = WhatcamTheme.typography.labelLargeR,
        )

        ClearButton(onClickClear = onClickClear)
    }
}

@Composable
private fun ClearButton(
    onClickClear: () -> Unit,
) {
    TextButton(onClick = onClickClear) {
        Text(
            text = stringResource(Res.string.notice_search_clear),
            color = Gray,
            style = WhatcamTheme.typography.labelMediumR,
        )
    }
}

@Composable
private fun SearchHistoryList(
    searchHistories: List<String>,
    onClickSearchHistory: (query: String) -> Unit,
    onClickDeleteHistory: (query: String) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = searchHistories,
            key = { query -> query }
        ) { query ->
            SearchHistoryItem(
                query = query,
                onClickItem = { onClickSearchHistory(query) },
                onClickDeleteHistory = onClickDeleteHistory
            )
        }
    }
}

@Composable
private fun SearchHistoryItem(
    query: String,
    onClickItem: () -> Unit,
    onClickDeleteHistory: (query: String) -> Unit,
) {
    Button(
        contentPadding = PaddingValues(horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Mint01A70),
        onClick = onClickItem,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = query,
                style = WhatcamTheme.typography.labelMediumR,
            )

            DeleteIconButton(
                modifier = Modifier.padding(start = 6.dp),
                onClick = { onClickDeleteHistory(query) },
            )
        }
    }
}

@Composable
private fun DeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.size(20.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            tint = White,
            contentDescription = null,
        )
    }
}
