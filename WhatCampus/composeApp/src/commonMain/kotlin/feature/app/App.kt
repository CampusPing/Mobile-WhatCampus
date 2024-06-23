package feature.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.app.components.WhatcamNavHost
import feature.app.navigation.rememberWhatcamNavigator
import core.designsystem.theme.WhatcamTheme

@Composable
fun App() {
    val navigator = rememberWhatcamNavigator()

    WhatcamTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .statusBarsPadding()
        ) {
            WhatcamNavHost(navigator = navigator)
        }
    }
}

