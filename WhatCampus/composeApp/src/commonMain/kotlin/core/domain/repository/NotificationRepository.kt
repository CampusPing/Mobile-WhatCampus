package core.domain.repository

import core.model.Notification
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun flowNotifications(): Flow<PersistentList<Notification>>
    suspend fun addNotification(notification: Notification)
    fun flowHasNewNotification(): Flow<Boolean>
    suspend fun updateHasNewNotification(hasNewNotification: Boolean)
    suspend fun readNotification(notificationId: Long)
}
