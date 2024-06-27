package feature.bookmark.model

import core.model.Notice
import kotlinx.collections.immutable.persistentListOf

data class BookmarkUiState(
    val notices: List<Notice> = persistentListOf(),
)
