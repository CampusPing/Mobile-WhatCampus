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
        val fakeUnreadNotificationArchive = NotificationArchive.NewNotice(
            notificationArchiveId = 1L,
            notice = Notice(
                1,
                "Notice 1",
                LocalDateTime(2021, 1, 1, 0, 0),
                "https://example.com/1"
            ),
            isRead = false,
            LocalDateTime(2024, 1, 1, 0, 0)
        )
        val fakeReadNotificationArchive = NotificationArchive.NewNotice(
            notificationArchiveId = 2L,
            notice = Notice(
                2,
                "Notice 2",
                LocalDateTime(2024, 1, 1, 0, 0),
                "https://example.com/1"
            ),
            isRead = true,
            LocalDateTime(2024, 8, 26, 0, 0)
        )

        emit(
            persistentListOf(
                fakeUnreadNotificationArchive,
                fakeReadNotificationArchive
            )
        )
    }
}
