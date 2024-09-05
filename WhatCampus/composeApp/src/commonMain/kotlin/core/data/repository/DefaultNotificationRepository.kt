package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.database.dao.NotificationDao
import core.database.entity.NotificationEntity
import core.database.mapper.toNotification
import core.database.mapper.toNotificationEntity
import core.datastore.key.NotificationKey
import core.domain.repository.NotificationRepository
import core.model.Notification
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultNotificationRepository(
    private val dataStore: DataStore<Preferences>,
    private val notificationDao: NotificationDao,
) : NotificationRepository {

    override fun flowNotifications(): Flow<PersistentList<Notification>> = notificationDao.getAll()
        .map { notifications -> notifications.map(NotificationEntity::toNotification) }
        .map { notifications -> notifications.toPersistentList() }

    override suspend fun addNotification(notification: Notification) {
        notificationDao.insert(notification.toNotificationEntity())
    }

    override fun flowHasNewNotification(): Flow<Boolean> = dataStore.data
        .map { pref -> pref[NotificationKey.hasNewNotification] ?: false }

    override suspend fun updateHasNewNotification(hasNewNotification: Boolean) {
        dataStore.edit { pref ->
            pref[NotificationKey.hasNewNotification] = hasNewNotification
        }
    }

    override suspend fun readNotification(notificationId: Long) {
        notificationDao.updateIsRead(id = notificationId)
    }
}
