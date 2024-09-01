package feature.campusmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.CampusMapRepository
import core.domain.repository.UserRepository
import feature.campusmap.model.CampusMapUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CampusMapViewModel(
    userRepository: UserRepository,
    campusMapRepository: CampusMapRepository,
) : ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = userRepository.flowUser()
        .filterNotNull()
        .map { user -> user.universityId }
        .flatMapLatest { campusId -> campusMapRepository.flowCampusMapUrl(campusId = campusId) }
        .map { campusMapUrl -> CampusMapUiState(campusMapUrl = campusMapUrl) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CampusMapUiState(campusMapUrl = "")
        )
}
