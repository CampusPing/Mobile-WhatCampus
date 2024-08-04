package feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.components.HighlightedText
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.di.koinViewModel
import feature.splash.model.SplashEvent
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.app_name
import whatcampus.composeapp.generated.resources.img_logo
import whatcampus.composeapp.generated.resources.splash_desc
import whatcampus.composeapp.generated.resources.splash_desc_highlight

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onSplashDone: (shouldOnboarding: Boolean) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is SplashEvent.SplashDone -> onSplashDone(uiEvent.shouldOnboarding)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(Res.drawable.img_logo),
            contentDescription = stringResource(Res.string.splash_desc),
            tint = WhatcamTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.size(20.dp))

        HighlightedText(
            fullText = stringResource(Res.string.splash_desc),
            highlightWords = listOf(
                stringResource(Res.string.app_name),
                stringResource(Res.string.splash_desc_highlight)
            ),
            defaultColor = Graphite,
            highlightColor = WhatcamTheme.colors.primary,
            textStyle = WhatcamTheme.typography.headlineSmallBL,
        )
    }
}
