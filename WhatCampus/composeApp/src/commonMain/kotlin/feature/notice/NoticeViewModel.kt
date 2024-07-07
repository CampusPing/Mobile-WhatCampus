package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.GetNoticeCategoriesByUniversityIdUseCase
import core.domain.usecase.GetNoticesByCategoryIdUseCase
import core.model.NoticeCategory
import feature.notice.model.NoticeUiState
import feature.notice.model.NoticeWithBookmark
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class NoticeViewModel(
    getNoticeCategoriesByUniversityId: GetNoticeCategoriesByUniversityIdUseCase,
    getNoticesByCategoryId: GetNoticesByCategoryIdUseCase,
    getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState: MutableStateFlow<NoticeUiState> = MutableStateFlow(NoticeUiState())
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()

    init {
        getNoticeCategoriesByUniversityId(universityId = 1)
            .map { noticeCategories ->
                uiState.value.copy(
                    noticeCategories = noticeCategories,
                    selectedCategory = noticeCategories.firstOrNull()
                )
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .onEach { universityUiState -> _uiState.value = universityUiState }
            .launchIn(viewModelScope)

        uiState
            .map { state -> state.selectedCategory }
            .filterNotNull()
            .flatMapLatest { noticeCategory -> getNoticesByCategoryId(noticeCategory.id) }
            .combine(getAllBookmarkedNotices()) { notices, bookmarkedNotices ->
                val bookmarkedNoticeIds = bookmarkedNotices.map { notice -> notice.id }.toImmutableSet()
                (notices to bookmarkedNoticeIds)
            }
            .map { (notices, bookmarkedNoticeIds) ->
                notices.map { notice ->
                    NoticeWithBookmark(
                        notice = notice,
                        isBookmarked = notice.id in bookmarkedNoticeIds
                    )
                }
            }
            .map { noticeWithBookmark ->
                _uiState.update { uiState -> uiState.copy(notices = noticeWithBookmark) }
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .launchIn(viewModelScope)
    }

    fun selectCategory(category: NoticeCategory) {
        val state = uiState.value
        if (category == state.selectedCategory) return

        _uiState.update {
            state.copy(selectedCategory = category)
        }
    }
}
