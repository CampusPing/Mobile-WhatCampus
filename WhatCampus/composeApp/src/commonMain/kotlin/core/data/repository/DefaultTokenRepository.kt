package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.mmk.kmpnotifier.notification.NotifierManager
import core.datastore.key.UserKey
import core.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultTokenRepository(
    private val dataStore: DataStore<Preferences>,
) : TokenRepository {

    override suspend fun saveFcmToken(fcmToken: String) {
        dataStore.edit { preferences ->
            preferences[UserKey.fcmToken] = fcmToken
        }
    }

    override fun getFcmToken(): Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[UserKey.fcmToken] ?: NotifierManager.getPushNotifier()
                .getToken()
                ?.also { fcmToken -> saveFcmToken(fcmToken) }
        }
}
