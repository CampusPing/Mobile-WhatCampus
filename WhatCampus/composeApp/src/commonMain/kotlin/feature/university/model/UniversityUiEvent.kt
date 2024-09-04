package feature.university.model

sealed interface UniversityUiEvent {
    data object UniversityLoadFailed : UniversityUiEvent
    data object UserSaveSuccess : UniversityUiEvent
}
