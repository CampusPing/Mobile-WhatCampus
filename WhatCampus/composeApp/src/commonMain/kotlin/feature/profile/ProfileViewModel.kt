package feature.profile

import androidx.lifecycle.ViewModel
import feature.profile.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()
}
