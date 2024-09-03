package feature.campusmap

import androidx.lifecycle.viewModelScope
import core.common.CommonViewModel
import core.common.CommonUiEvent
import core.domain.repository.CampusMapRepository
import core.domain.repository.UserRepository
import core.model.Response
import feature.campusmap.model.CampusMapUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.StateFlow

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
                Response.Failure.ClientError -> _commonUiEvent.emit(CommonUiEvent.ClientErrorOccurred)
                Response.Failure.ServerError -> _commonUiEvent.emit(CommonUiEvent.ServerErrorOccurred)
                Response.Failure.NetworkError -> _commonUiEvent.emit(CommonUiEvent.NetworkErrorOccurred)
                is Response.Failure.OtherError<*> -> _commonUiEvent.emit(CommonUiEvent.OtherErrorOccurred)
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
