package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.data.common.safeGet
import core.data.common.safePatch
import core.data.mapper.toNotifications
import core.data.model.NotificationsDto
import core.datastore.key.NotificationKey
import core.domain.repository.NotificationRepository
import core.model.Notification
import core.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultNotificationRepository(
    private val dataStore: DataStore<Preferences>,
    private val httpClient: HttpClient,
) : NotificationRepository {

    override fun flowNotifications(
        userId: Long,
    ): Flow<Response<PersistentList<Notification>>> = flow {
        val requestUrl = "/api/v1/notifications"
        val response = httpClient.safeGet<NotificationsDto>(
            urlString = requestUrl,
            block = { parameter("memberId", userId) },
        )

        emit(response.map { it.toNotifications() })
    }

    override fun flowHasNewNotification(): Flow<Boolean> = dataStore.data
        .map { pref -> pref[NotificationKey.hasNewNotification] ?: false }

    override suspend fun updateHasNewNotification(
        hasNewNotification: Boolean,
    ) {
        dataStore.edit { pref ->
            pref[NotificationKey.hasNewNotification] = hasNewNotification
        }
    }

    override suspend fun readNotification(
        notificationId: Long,
    ) {
        val requestUrl = "/api/v1/notifications/$notificationId/read"
        httpClient.safePatch<Unit>(requestUrl)
    }
}
