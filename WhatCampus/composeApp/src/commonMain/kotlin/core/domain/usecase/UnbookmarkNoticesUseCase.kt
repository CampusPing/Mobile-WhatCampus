package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice

data class UnbookmarkNoticesUseCase(
    val repository: NoticeRepository,
) {
    suspend operator fun invoke(notices: List<Notice>) {
        repository.unbookmarkNotices(notices = notices)
    }
}
