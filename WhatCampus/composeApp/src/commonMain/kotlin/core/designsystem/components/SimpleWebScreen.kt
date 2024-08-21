package core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.app_name
import whatcampus.composeapp.generated.resources.img_logo

@Composable
fun SimpleWebScreen(
    modifier: Modifier = Modifier,
    url: String,
    topBar: @Composable () -> Unit = { DefaultTopAppBar() },
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .then(modifier),
        topBar = topBar,
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            WebView(url = url)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultTopAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.img_logo),
                    contentDescription = stringResource(Res.string.app_name),
                    tint = WhatcamTheme.colors.primary,
                    modifier = Modifier.size(40.dp),
                )

                Spacer(modifier = Modifier.padding(start = 4.dp))

                Text(
                    text = stringResource(Res.string.app_name),
                    style = WhatcamTheme.typography.titleLargeB,
                    color = Graphite,
                    modifier = Modifier.absoluteOffset { IntOffset(x = 0, y = -8) },
                )
            }
        },
    )
}
