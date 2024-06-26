package feature.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.theme.Graphite
import feature.notice.components.WebView
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.university_title

@Composable
internal fun NoticeDetailScreen(
    modifier: Modifier = Modifier,
    noticeId: Long,
    noticeUrl: String,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NoticeDetailTopBar(
                onClickBack = onClickBack,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            WebView(url = noticeUrl)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoticeDetailTopBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {},
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
