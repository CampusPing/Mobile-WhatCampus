package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetAllNoticeCategoryUseCase
import core.domain.usecase.GetNoticesByCategoryIdUseCase
import core.model.NoticeCategory
import feature.notice.model.NoticeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class NoticeViewModel(
    getAllNoticeCategory: GetAllNoticeCategoryUseCase,
    getNoticesByCategoryId: GetNoticesByCategoryIdUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState: MutableStateFlow<NoticeUiState> = MutableStateFlow(NoticeUiState.Loading)
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()

    init {
        getAllNoticeCategory(universityId = 1)
            .map { noticeCategories ->
                NoticeUiState.Success(
                    noticeCategories = noticeCategories,
                    selectedCategory = noticeCategories.firstOrNull(),
                )
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .onEach { universityUiState -> _uiState.value = universityUiState }
            .launchIn(viewModelScope)

        uiState
            .map { state -> state as? NoticeUiState.Success }
            .map { state -> state?.selectedCategory }
            .filterNotNull()
            .flatMapLatest { noticeCategory -> getNoticesByCategoryId(noticeCategory.id) }
            .map { notices ->
                _uiState.update { uiState ->
                    (uiState as NoticeUiState.Success).copy(notices = notices)
                }
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .launchIn(viewModelScope)
    }

    fun selectCategory(category: NoticeCategory) {
        val state = uiState.value
        if (state !is NoticeUiState.Success) return
        if (category == state.selectedCategory) return

        _uiState.update {
            state.copy(selectedCategory = category)
        }
    }
}
