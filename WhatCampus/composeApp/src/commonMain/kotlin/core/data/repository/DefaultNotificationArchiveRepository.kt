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
            title = "[장학] 2024학년도 1학기 장학금 신청 안내",
            isRead = false,
            datetime = LocalDateTime(2024, 1, 1, 0, 0),
            notice = Notice(
                1,
                "Notice 1",
                LocalDateTime(2021, 1, 1, 0, 0),
                "https://example.com/1"
            )
        )
        val fakeReadNotificationArchive = NotificationArchive.NewNotice(
            notificationArchiveId = 2L,
            title = "[장학] C프로그래밍 수강 인원 증설 안내 / 컴퓨터과학과 학생 필독",
            isRead = true,
            datetime = LocalDateTime(2024, 8, 26, 0, 0),
            notice = Notice(
                2,
                "Notice 2",
                LocalDateTime(2024, 1, 1, 0, 0),
                "https://example.com/1"
            )
        )

        emit(
            persistentListOf(
                fakeUnreadNotificationArchive,
                fakeReadNotificationArchive
            )
        )
    }
}
