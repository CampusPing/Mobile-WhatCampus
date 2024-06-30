package feature.noticeCategory

import androidx.lifecycle.ViewModel
import feature.noticeCategory.model.NoticeCategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoticeCategoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NoticeCategoryUiState())
    val uiState = _uiState.asStateFlow()


}
