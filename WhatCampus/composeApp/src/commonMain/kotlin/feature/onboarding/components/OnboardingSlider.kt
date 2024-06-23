package feature.onboarding.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.designsystem.theme.LightGray
import core.designsystem.theme.WhatcamTheme
import feature.onboarding.OnboardingSliderItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingSlider(
    modifier: Modifier = Modifier,
    sliderItems: List<OnboardingSliderItem>,
    pagerState: PagerState,
    contentPadding: PaddingValues,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = contentPadding,
        ) { page ->
            val onboardingImageSize by animateFloatAsState(
                targetValue = if (pagerState.currentPage == page) 1f else 0.8f,
                animationSpec = tween(durationMillis = 300)
            )

            OnboardingItem(
                imageSize = onboardingImageSize,
                sliderItem = sliderItems[page]
            )
        }

        HorizontalSlidingIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 40.dp),
        )
    }
}

@Composable
private fun OnboardingItem(
    imageSize: Float,
    sliderItem: OnboardingSliderItem,
) {
    val image = painterResource(sliderItem.imageRes)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2F)
                .graphicsLayer {
                    scaleX = imageSize
                    scaleY = imageSize
                },
        )
        Text(
            text = stringResource(sliderItem.descriptionRes),
            style = WhatcamTheme.typography.labelLargeB,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 40.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalSlidingIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    dotSize: Dp = 8.dp,
    spacing: Dp = 8.dp,
) {
    val indicatorDistancePx = with(LocalDensity.current) {
        (dotSize + spacing).toPx()
    }

    val inactiveBackground = LightGray
    val activeBackground = WhatcamTheme.colors.primary

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(pagerState.pageCount) {
                Box(
                    modifier = Modifier
                        .background(color = inactiveBackground, shape = CircleShape)
                        .size(dotSize),
                )
            }
        }
        Box(
            modifier = Modifier
                .horizontalSlidingTransition(pagerState = pagerState, distance = indicatorDistancePx)
                .size(size = dotSize)
                .background(color = activeBackground, shape = CircleShape),
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.horizontalSlidingTransition(
    pagerState: PagerState,
    distance: Float,
) = graphicsLayer {
    val pageOffset = pagerState.currentPage + pagerState.currentPageOffsetFraction
    translationX = pageOffset * distance
}
