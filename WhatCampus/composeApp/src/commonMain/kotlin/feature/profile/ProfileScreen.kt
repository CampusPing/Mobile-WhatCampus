package feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

}
