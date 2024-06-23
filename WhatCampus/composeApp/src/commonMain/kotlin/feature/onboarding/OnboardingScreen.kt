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
import feature.onboarding.component.OnboardingSlider
import org.jetbrains.compose.resources.stringResource
import theme.WhatcamTheme
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.onboarding_start
import whatcampus.composeapp.generated.resources.onboarding_title

@Composable
internal fun OnboardingRoute(
    modifier: Modifier = Modifier,
    onboardingComplete: () -> Unit,
) {
    OnboardingScreen(
        modifier = modifier,
        onboardingComplete = onboardingComplete,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onboardingComplete: () -> Unit,
) {
    val sliderItems = OnboardingSliderItem.entries
    val pagerState = rememberPagerState { sliderItems.size }
    val horizontalPadding = PaddingValues(horizontal = 20.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 40.dp)
    ) {
        Text(
            text = stringResource(Res.string.onboarding_title),
            style = WhatcamTheme.typography.titleLargeBL,
            modifier = Modifier.padding(horizontalPadding),
        )

        OnboardingSlider(
            modifier = Modifier.weight(1F),
            sliderItems = sliderItems,
            pagerState = pagerState,
            contentPadding = horizontalPadding,
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
