package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

data class BookmarkNoticeUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(notice: Notice): Flow<Notice?> {
        return repository
            .bookmarkNotice(notice)
            .map { notice as Notice? }
            .catch { emit(null) }
    }
}
