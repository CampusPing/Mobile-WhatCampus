package feature.university.model

sealed interface UniversityUiEvent {
    data object UNIVERTITY_LOAD_FAILED : UniversityUiEvent
    data object USER_SAVE_FAILED : UniversityUiEvent
    data object USER_SAVE_SUCCESS : UniversityUiEvent
}
