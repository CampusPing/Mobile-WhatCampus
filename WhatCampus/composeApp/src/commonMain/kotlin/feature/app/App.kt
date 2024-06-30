package feature.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import core.common.extensions.showSnackbar
import core.common.util.newImageLoader
import core.designsystem.theme.WhatcamTheme
import feature.app.components.WhatcamNavHost
import feature.app.navigation.rememberWhatcamNavigator

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(
    debug: Boolean = false,
) {
    val navigator = rememberWhatcamNavigator()
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    setSingletonImageLoaderFactory { context ->
        newImageLoader(context, debug)
    }

    WhatcamTheme {
        Box(
            modifier = Modifier
                .background(color = WhatcamTheme.colors.background)
                .fillMaxSize()
                .systemBarsPadding()
                .statusBarsPadding()
        ) {
            WhatcamNavHost(
                navigator = navigator,
                onShowSnackbar = { message, actionLabel ->
                    coroutineScope.showSnackbar(
                        snackBarState = snackBarState,
                        message = message,
                        actionLabel = actionLabel
                    )
                }
            )

            SnackbarHost(
                hostState = snackBarState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 72.dp)
            )
        }
    }
}
