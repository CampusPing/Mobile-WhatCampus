package core.domain.usecase

import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class IsBookmarkedNoticeUseCase(
    private val getAllBookmarkedNotices: GetAllBookmarkedNoticesUseCase,
) {
    operator fun invoke(notice: Notice): Flow<Boolean> {
        return getAllBookmarkedNotices()
            .map { bookmarkedNotices ->
                bookmarkedNotices.find { bookmarkedNotice ->
                    bookmarkedNotice.id == notice.id
                }
            }.map { bookmarkedNotice ->
                bookmarkedNotice != null
            }
    }
}

