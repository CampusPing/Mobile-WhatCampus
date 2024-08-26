package core.data.repository

import core.domain.repository.NotificationArchiveRepository
import core.model.Notice
import core.model.NotificationArchive
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDateTime

class DefaultNotificationArchiveRepository : NotificationArchiveRepository {

    override fun flowNotificationArchive(): Flow<PersistentList<NotificationArchive>> = flow {
        val fakeNotificationArchive = NotificationArchive.NewNotice(
            id = 1L,
            notice = Notice(
                1,
                "Notice 1",
                LocalDateTime(2021, 1, 1, 0, 0),
                "https://example.com/1"
            ),
            isRead = false,
        )

        emit(
            persistentListOf(
                fakeNotificationArchive.copy(id = 1L),
                fakeNotificationArchive.copy(id = 2L),
                fakeNotificationArchive.copy(id = 3L),
                fakeNotificationArchive.copy(id = 4L, isRead = false),
                fakeNotificationArchive.copy(id = 5L, isRead = false),
                fakeNotificationArchive.copy(id = 6L),
            )
        )
    }
}