package feature.chat.components

import KottieAnimation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.Gray
import core.designsystem.theme.WhatcamTheme
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.chat_preparation_content
import whatcampus.composeapp.generated.resources.chat_preparation_title

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ChatPreparation(
    modifier: Modifier,
) {
    var emptyAnimation by remember { mutableStateOf("") }
    val composition = rememberKottieComposition(
        spec = KottieCompositionSpec.File(emptyAnimation)
    )
    val animationState by animateKottieCompositionAsState(
        composition = composition,
        speed = 0.5f,
        iterations = Int.MAX_VALUE,
    )

    LaunchedEffect(Unit) {
        emptyAnimation = Res.readBytes(path = LOTTIE_ANIM_FILE_PATH).decodeToString()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KottieAnimation(
            composition = composition,
            progress = { animationState.progress },
            modifier = Modifier
                .fillMaxSize(0.5f)
                .sizeIn(300.dp, 300.dp),
        )

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = stringResource(Res.string.chat_preparation_title),
            style = WhatcamTheme.typography.titleLargeB,
            color = Graphite,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = stringResource(Res.string.chat_preparation_content),
            style = WhatcamTheme.typography.bodyLargeR,
            color = Gray,
            textAlign = TextAlign.Center,
        )
    }
}

private const val LOTTIE_ANIM_FILE_PATH = "files/anim_chat_preparation.json"
