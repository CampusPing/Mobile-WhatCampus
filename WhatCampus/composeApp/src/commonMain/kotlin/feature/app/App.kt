package feature.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.theme.WhatcamTheme
import feature.app.components.WhatcamNavHost
import feature.app.navigation.rememberWhatcamNavigator

@Composable
fun App() {
    val navigator = rememberWhatcamNavigator()

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

