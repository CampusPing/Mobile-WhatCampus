package feature.campusmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import core.common.extensions.collectAsStateMultiplatform
import core.common.extensions.collectUiEvent
import core.di.koinViewModel
import feature.campusmap.components.ZoomableImage
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.campusmap_tab_title

@Composable
internal fun CampusMapScreen(
    viewModel: CampusMapViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    viewModel.commonUiEvent.collectUiEvent()

    ZoomableImage(
        image = uiState.campusMapUrl,
        contentDescription = stringResource(Res.string.campusmap_tab_title),
    )
}
