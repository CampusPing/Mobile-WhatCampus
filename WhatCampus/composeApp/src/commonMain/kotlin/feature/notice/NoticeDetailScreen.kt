package feature.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.icons.filled.Bookmark
import core.designsystem.icons.outlined.Bookmark
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import core.di.koinViewModel
import core.model.Notice
import feature.notice.components.WebView
import feature.notice.model.NoticeDetailUiState
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.bookmark_tab_title
import whatcampus.composeapp.generated.resources.university_title

@Composable
internal fun NoticeDetailScreen(
    viewModel: NoticeDetailViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    notice: Notice,
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    viewModel.setup(notice)

    when (val state = uiState) {
        NoticeDetailUiState.Loading -> {
            // Loading state
        }

        is NoticeDetailUiState.Success -> NoticeDetailScreen(
            modifier = modifier,
            noticeUrl = notice.url,
            onClickBack = onClickBack,
            onClickBookmark = { viewModel.toggleBookmark(notice) },
            isBookmarked = state.isBookmarked,
        )
    }
}

@Composable
private fun NoticeDetailScreen(
    modifier: Modifier,
    noticeUrl: String,
    onClickBack: () -> Unit,
    onClickBookmark: () -> Unit,
    isBookmarked: Boolean,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NoticeDetailTopBar(
                onClickBack = onClickBack,
                onClickBookmark = onClickBookmark,
                isBookmarked = isBookmarked,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            WebView(url = noticeUrl)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoticeDetailTopBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickBookmark: () -> Unit,
    isBookmarked: Boolean,
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.university_title),
                    tint = Graphite,
                )
            }
        },
        actions = {
            IconButton(onClick = onClickBookmark) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.Bookmark,
                    contentDescription = stringResource(Res.string.bookmark_tab_title),
                    tint = if (isBookmarked) WhatcamTheme.colors.primary else Graphite,
                    modifier = Modifier.padding(4.dp),
                )
            }
        },
    )
}
