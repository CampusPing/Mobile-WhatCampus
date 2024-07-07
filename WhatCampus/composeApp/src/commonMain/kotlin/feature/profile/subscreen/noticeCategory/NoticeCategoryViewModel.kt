package feature.profile.subscreen.noticeCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetNoticeCategoriesByUniversityIdUseCase
import core.domain.usecase.GetSubscribedNoticeCategoriesUseCase
import core.domain.usecase.SubscribeNoticeCategoriesUseCase
import core.model.NoticeCategory
import feature.profile.subscreen.noticeCategory.model.NoticeCategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class NoticeCategoryViewModel(
    getNoticeCategoriesByUniversity: GetNoticeCategoriesByUniversityIdUseCase,
    getSubscribedNoticeCategories: GetSubscribedNoticeCategoriesUseCase,
    private val subscribeNoticeCategories: SubscribeNoticeCategoriesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoticeCategoryUiState())
    val uiState: StateFlow<NoticeCategoryUiState> = _uiState.asStateFlow()

    init {
        combine(
            getSubscribedNoticeCategories(userId = 1),
            getNoticeCategoriesByUniversity(universityId = 1),
        ) { subscribedNoticeCategories, noticeCategories ->
            val fetchedUiState = NoticeCategoryUiState(
                subscribedNoticeCategories = subscribedNoticeCategories,
                noticeCategories = noticeCategories,
            )
            _uiState.update { fetchedUiState }
        }.launchIn(viewModelScope)
    }

    fun toggleNoticeCategory(noticeCategory: NoticeCategory) {
        _uiState.update { uiState ->
            uiState.toggleSelectedNoticeCategory(noticeCategory)
        }
    }

    fun subscribeNoticeCategories() {
        subscribeNoticeCategories(noticeCategories = uiState.value.subscribedNoticeCategories)
    }
}
