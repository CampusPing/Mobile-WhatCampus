package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.database.dao.NotificationDao
import core.database.mapper.toNotificationEntity
import core.datastore.key.NotificationKey
import core.domain.repository.NotificationRepository
import core.model.Notice
import core.model.Notification
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

class DefaultNotificationRepository(
    private val dataStore: DataStore<Preferences>,
    private val notificationDao: NotificationDao,
) : NotificationRepository {

    override fun flowNotifications(): Flow<PersistentList<Notification>> = flow {
        val fakeUnreadNotification = Notification.NewNotice(
            notificationId = 1L,
            content = "[장학] 2024학년도 1학기 장학금 신청 안내",
            isRead = false,
            receivedDatetime = LocalDateTime(2024, 1, 1, 0, 0),
            notice = Notice(
                1,
                "Notice 1",
                LocalDateTime(2021, 1, 1, 0, 0),
                "https://example.com/1"
            )
        )
        val fakeReadNotification = Notification.NewNotice(
            notificationId = 2L,
            content = "[장학] C프로그래밍 수강 인원 증설 안내 / 컴퓨터과학과 학생 필독",
            isRead = true,
            receivedDatetime = LocalDateTime(2024, 8, 26, 0, 0),
            notice = Notice(
                2,
                "Notice 2",
                LocalDateTime(2024, 1, 1, 0, 0),
                "https://example.com/1"
            )
        )

        emit(
            persistentListOf(
                fakeUnreadNotification,
                fakeReadNotification
            )
        )
    }

    override suspend fun addNotification(notification: Notification) {
        notificationDao.insert(notification.toNotificationEntity())
    }

    override fun flowHasNewNotification(): Flow<Boolean> = dataStore.data
        .map { pref -> pref[NotificationKey.hasNewNotification] ?: true }

    override suspend fun updateHasNewNotification(hasNewNotification: Boolean) {
        dataStore.edit { pref ->
            pref[NotificationKey.hasNewNotification] = hasNewNotification
        }
    }

    override suspend fun readNotification(notificationId: Long) {
        // TODO: ktor 읽음 처리 구현
    }
}
