package core.domain.repository

import core.model.NotificationArchive
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface NotificationArchiveRepository {
    fun flowNotificationArchive(): Flow<PersistentList<NotificationArchive>>
    fun flowHasNewNotification(): Flow<Boolean>
    suspend fun updateHasNewNotification(hasNewNotification: Boolean)
    suspend fun readNotification(notificationId: Long)
}
