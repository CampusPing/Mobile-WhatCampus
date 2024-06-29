package feature.noticeSearch.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_search_title
import whatcampus.composeapp.generated.resources.notice_tab_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoticeSearchTopBar(
    onClickBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.notice_search_title),
                style = WhatcamTheme.typography.titleMediumB,
                color = Graphite,
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.notice_tab_title),
                    tint = Graphite,
                )
            }
        }
    )
}
