package feature.university

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.img_party
import whatcampus.composeapp.generated.resources.university_complete_desc
import whatcampus.composeapp.generated.resources.university_complete_start
import whatcampus.composeapp.generated.resources.university_complete_title
import whatcampus.composeapp.generated.resources.university_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UniversityCompleteScreen(
    modifier: Modifier = Modifier,
    onClickComplete: () -> Unit,
    onClickBack: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val completeImageScale by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.university_title),
                            tint = Graphite,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(Res.string.university_complete_title),
                style = WhatcamTheme.typography.headlineMediumM,
                color = Mint01,
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = stringResource(Res.string.university_complete_desc),
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
            )

            Image(
                painter = painterResource(Res.drawable.img_party),
                contentDescription = stringResource(Res.string.university_complete_desc),
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth(fraction = completeImageScale)
                    .heightIn(max = 200.dp)
                    .aspectRatio(1F)
                    .align(Alignment.CenterHorizontally)
                    .weight(1F)
            )

            CompleteButton(onClick = onClickComplete)
        }
    }
}

@Composable
private fun CompleteButton(
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
    ) {
        Text(
            text = stringResource(Res.string.university_complete_start),
            style = WhatcamTheme.typography.bodyLargeB
        )
    }
}
