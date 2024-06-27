package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetAllBookmarkedNoticesUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(): Flow<List<Notice>> {
        return repository
            .flowBookmarkedNotices()
            .map { notices ->
                notices.sortedByDescending { notice -> notice.datetime }
            }
    }
}
