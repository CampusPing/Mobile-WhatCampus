package core.domain.usecase

import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class IsBookmarkedNoticeUseCase(
    private val getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
) {
    operator fun invoke(
        notice: Notice,
    ): Flow<Boolean> = getAllBookmarkedNotices().map { bookmarkedNotices ->
        notice.isBookmarked(bookmarkedNotices)
    }

    private fun Notice.isBookmarked(
        bookmarkedNotices: List<Notice>,
    ): Boolean {
        return bookmarkedNotices.findBookmarkedNotice(this) != null
    }

    private fun List<Notice>.findBookmarkedNotice(
        notice: Notice,
    ): Notice? {
        return find { bookmarkedNotice -> bookmarkedNotice.id == notice.id }
    }
}

