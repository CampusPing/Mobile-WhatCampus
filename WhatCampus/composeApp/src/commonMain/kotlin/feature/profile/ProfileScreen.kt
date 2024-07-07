package feature.profile

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import feature.profile.components.ProfileTopBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

    Scaffold(
        modifier = modifier,
        topBar = {
            ProfileTopBar(onClickBack = onClickBack)
        },
    ) { paddingValues ->

    }
}
