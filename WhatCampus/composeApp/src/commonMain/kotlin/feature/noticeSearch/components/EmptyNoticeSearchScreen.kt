package feature.noticeSearch.components

import KottieAnimation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.img_logo
import whatcampus.composeapp.generated.resources.notice_empty_content
import whatcampus.composeapp.generated.resources.notice_empty_title

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun EmptyNoticeSearchScreen(
    modifier: Modifier = Modifier,
) {
    var emptyAnimation by remember { mutableStateOf("") }
    val composition = rememberKottieComposition(
        spec = KottieCompositionSpec.File(emptyAnimation)
    )
    val animationState by animateKottieCompositionAsState(
        composition = composition,
        speed = 0.4f,
        iterations = Int.MAX_VALUE,
    )

    LaunchedEffect(Unit) {
        emptyAnimation = Res.readBytes(path = EMPTY_ANIM_FILE_PATH).decodeToString()
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

        EmptyTitle()

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = stringResource(Res.string.notice_empty_content),
            style = WhatcamTheme.typography.bodyLargeR,
            color = Gray,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun EmptyTitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = stringResource(Res.string.notice_empty_title),
            style = WhatcamTheme.typography.titleLargeB,
            color = Graphite,
            textAlign = TextAlign.Center,
        )

        Icon(
            painter = painterResource(Res.drawable.img_logo),
            contentDescription = null,
            modifier = Modifier.size(36.dp),
            tint = WhatcamTheme.colors.primary,
        )
    }
}

private const val EMPTY_ANIM_FILE_PATH = "files/anim_empty.json"
