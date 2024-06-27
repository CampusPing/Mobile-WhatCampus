package core.domain.usecase

import core.domain.repository.NoticeRepository
import core.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

data class UnbookmarkNoticeUseCase(
    private val repository: NoticeRepository,
) {
    operator fun invoke(notice: Notice): Flow<Boolean> {
        return repository
            .unbookmarkNotice(notice)
            .map { true }
            .catch { emit(false) }
    }
}
