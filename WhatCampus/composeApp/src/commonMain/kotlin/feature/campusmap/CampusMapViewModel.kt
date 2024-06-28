package feature.campusmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetCampusMapUrlUseCase
import feature.campusmap.model.CampusMapUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CampusMapViewModel(
    getCampusMapUrl: GetCampusMapUrlUseCase,
) : ViewModel() {
    val uiState = getCampusMapUrl(campusId = 1)
        .map { campusMapUrl -> CampusMapUiState(campusMapUrl = campusMapUrl) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CampusMapUiState(campusMapUrl = "")
        )
}
