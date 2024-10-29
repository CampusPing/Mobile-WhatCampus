package core.designsystem.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.designsystem.theme.WhatcamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.WhatCamPullToRefreshContainer(
    refreshState: PullToRefreshState,
) {
    PullToRefreshContainer(
        state = refreshState,
        modifier = Modifier.align(Alignment.TopCenter),
        containerColor = WhatcamTheme.colors.primaryContainer,
        contentColor = WhatcamTheme.colors.primary,
    )
}
