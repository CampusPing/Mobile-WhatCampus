package core.domain.repository

import core.model.Notification
import core.model.Response
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun flowNotifications(
        userId: Long,
    ): Flow<Response<PersistentList<Notification>>>

    fun flowHasNewNotification(): Flow<Boolean>

    suspend fun updateHasNewNotification(hasNewNotification: Boolean)

    suspend fun readNotification(
        userId: Long,
        notificationId: Long,
    )
}
