package feature.bookmark.model

import core.model.Notice
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

data class BookmarkUiState(
    val notices: List<Notice> = persistentListOf(),
    val markedNoticesForDelete: Set<Notice> = persistentSetOf(),
) {
    val isAllNoticesMarkedForDelete: Boolean
        get() = markedNoticesForDelete.size == notices.size
}
