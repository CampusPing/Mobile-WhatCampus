package feature.noticeCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetNoticeCategoriesByUniversityIdUseCase
import core.model.NoticeCategory
import feature.noticeCategory.model.NoticeCategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NoticeCategoryViewModel(
    getNoticeCategoriesByUniversity: GetNoticeCategoriesByUniversityIdUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoticeCategoryUiState())
    val uiState: StateFlow<NoticeCategoryUiState> = _uiState.asStateFlow()

    init {
        getNoticeCategoriesByUniversity(universityId = 1)
            .onEach { noticeCategories ->
                _uiState.update { NoticeCategoryUiState(noticeCategoryList = noticeCategories) }
            }
            .launchIn(viewModelScope)
    }

    fun toggleNoticeCategory(noticeCategory: NoticeCategory) {
        _uiState.update { uiState ->
            uiState.toggleSelectedNoticeCategory(noticeCategory)
        }
    }
}
