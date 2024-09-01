package feature.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.NoticeRepository
import core.domain.repository.NotificationRepository
import core.domain.repository.UserRepository
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.GetNoticesByCategoryIdUseCase
import core.model.NoticeCategory
import feature.notice.model.NoticeUiState
import feature.notice.model.NoticeWithBookmark
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class NoticeViewModel(
    noticeRepository: NoticeRepository,
    notificationRepository: NotificationRepository,
    getNoticesByCategoryId: GetNoticesByCategoryIdUseCase,
    userRepository: UserRepository,
    getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<NoticeUiState> = MutableStateFlow(NoticeUiState())
    val uiState: StateFlow<NoticeUiState> = _uiState.asStateFlow()

    init {
        userRepository.flowUser()
            .filterNotNull()
            .flatMapLatest { user ->
                noticeRepository.flowNoticeCategory(universityId = user.universityId)
                    .map { noticeCategories ->
                        uiState.value.copy(
                            user = user,
                            noticeCategories = noticeCategories,
                            selectedCategory = noticeCategories.first(),
                        )
                    }
            }
            .onEach { universityUiState -> _uiState.value = universityUiState }
            .launchIn(viewModelScope)

        notificationRepository.flowHasNewNotification()
            .onEach { hasNewNotification ->
                _uiState.update { uiState -> uiState.copy(hasNewNotification = hasNewNotification) }
            }
            .launchIn(viewModelScope)

        uiState
            .flatMapLatest { state ->
                val universityId = state.user?.universityId ?: return@flatMapLatest emptyFlow()
                val categoryId = state.selectedCategory?.id ?: return@flatMapLatest emptyFlow()

                getNoticesByCategoryId(universityId, categoryId)
            }
            .combine(getAllBookmarkedNotices()) { notices, bookmarkedNotices ->
                val bookmarkedNoticeIds =
                    bookmarkedNotices.map { notice -> notice.id }.toImmutableSet()
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
