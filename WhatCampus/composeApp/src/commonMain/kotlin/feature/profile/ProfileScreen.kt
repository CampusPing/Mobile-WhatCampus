package feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import core.common.extensions.collectAsStateMultiplatform
import core.di.koinViewModel
import feature.profile.components.ProfileTopBar
import feature.profile.components.UserInformation

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
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            UserInformation(
                universityName = uiState.user.universityName,
                departmentName = uiState.user.departmentName,
            )
        }
    }
}
