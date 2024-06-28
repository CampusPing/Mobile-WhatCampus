package feature.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
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
            WhatcamNavHost(navigator = navigator)
        }
    }
}

