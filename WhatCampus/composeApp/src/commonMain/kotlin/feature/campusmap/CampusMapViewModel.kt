package feature.campusmap

import androidx.lifecycle.viewModelScope
import core.common.CommonViewModel
import core.domain.repository.CampusMapRepository
import core.domain.repository.UserRepository
import core.model.Response
import feature.campusmap.model.CampusMapUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class CampusMapViewModel(
    userRepository: UserRepository,
    campusMapRepository: CampusMapRepository,
) : CommonViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<CampusMapUiState> = userRepository.flowUser()
        .filterNotNull()
        .map { user -> user.universityId }
        .flatMapLatest { campusId -> campusMapRepository.flowCampusMapUrl(campusId = campusId) }
        .onEach { campusMapUrlResponse ->
            when (campusMapUrlResponse) {
                is Response.Success -> Unit
                Response.Failure.ClientError -> sendClientErrorEvent()
                Response.Failure.ServerError -> sendServerErrorEvent()
                Response.Failure.NetworkError -> sendNetworkErrorEvent()
                is Response.Failure.OtherError<*> -> sendOtherErrorEvent()
            }
        }
        .map { campusMapUrlResponse ->
            when (campusMapUrlResponse) {
                is Response.Success -> CampusMapUiState(campusMapUrl = campusMapUrlResponse.body)
                else -> CampusMapUiState.Default
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CampusMapUiState.Default
        )
}
