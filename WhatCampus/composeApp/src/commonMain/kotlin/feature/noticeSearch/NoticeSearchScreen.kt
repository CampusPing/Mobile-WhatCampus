package feature.noticeSearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.di.koinViewModel
import core.model.Notice

@Composable
internal fun NoticeSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: NoticeSearchViewModel = koinViewModel(),
    onClickBack: () -> Unit,
    onClickNotice: (Notice) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {

    }
}
