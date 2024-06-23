package feature.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.WhatcamTheme
import feature.onboarding.components.OnboardingSlider
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.onboarding_start
import whatcampus.composeapp.generated.resources.onboarding_title

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onboardingComplete: () -> Unit,
) {
    val sliderItems = OnboardingSliderItem.entries
    val pagerState = rememberPagerState { sliderItems.size }
    val horizontalPadding = PaddingValues(horizontal = 20.dp)

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.onboarding_title),
            style = WhatcamTheme.typography.headlineMediumM,
            modifier = Modifier.padding(horizontalPadding),
        )

        OnboardingSlider(
            modifier = Modifier.weight(1F),
            sliderItems = sliderItems,
            pagerState = pagerState,
        )

        OnboardingCompleteButton(
            onboardingComplete = onboardingComplete,
            horizontalPadding = horizontalPadding,
            pagerState = pagerState,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingCompleteButton(
    onboardingComplete: () -> Unit,
    horizontalPadding: PaddingValues,
    pagerState: PagerState,
) {
    Button(
        onClick = onboardingComplete,
        modifier = Modifier
            .padding(horizontalPadding)
            .fillMaxWidth()
            .height(52.dp),
        enabled = pagerState.currentPage == pagerState.pageCount - 1,
    ) {
        Text(
            text = stringResource(Res.string.onboarding_start),
            style = WhatcamTheme.typography.bodyLargeB
        )
    }
}
