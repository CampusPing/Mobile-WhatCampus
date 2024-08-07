package feature.noticeCategory.model

sealed interface NoticeCategoryUiEvent {
    data object NavigateToOnboarding : NoticeCategoryUiEvent
}
