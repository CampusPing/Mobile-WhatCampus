package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.mmk.kmpnotifier.notification.NotifierManager
import core.datastore.key.UserKey
import core.domain.repository.TokenRepository
import core.domain.repository.UserRepository
import core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultUserRepository(
    // private val ktorClient: KtorClient,
    private val dataStore: DataStore<Preferences>,
    private val tokenRepository: TokenRepository,
) : UserRepository {

    override fun flowUser(): Flow<User?> = dataStore.data
        .map { pref ->
            val userId = pref[UserKey.userId] ?: return@map null
            val universityId = pref[UserKey.universityId] ?: return@map null
            val universityName = pref[UserKey.universityName] ?: return@map null
            val departmentId = pref[UserKey.departmentId] ?: return@map null
            val departmentName = pref[UserKey.departmentName] ?: return@map null
            val isPushNotificationAllowed = pref[UserKey.isPushNotificationAllowed] ?: true
            val fcmToken = tokenRepository.getFcmToken().firstOrNull() ?: return@map null

            User(
                userId = userId,
                universityId = universityId,
                universityName = universityName,
                departmentId = departmentId,
                departmentName = departmentName,
                fcmToken = fcmToken,
                isPushNotificationAllowed = isPushNotificationAllowed,
            )
        }

    override fun createUser(
        universityId: Long,
        universityName: String,
        departmentId: Long,
        departmentName: String,
    ): Flow<Long> {
        return flow {
            // ktorClient.saveUser(user) :: userId를 반환 할 예정
            emit(1L)
        }.map { userId ->
            dataStore.edit { pref ->
                pref[UserKey.userId] = userId
                pref[UserKey.universityId] = universityId
                pref[UserKey.universityName] = universityName
                pref[UserKey.departmentId] = departmentId
                pref[UserKey.departmentName] = departmentName
                pref[UserKey.isPushNotificationAllowed] = true
            }
            val fcmToken = NotifierManager.getPushNotifier().getToken()
            if (fcmToken != null) tokenRepository.saveFcmToken(fcmToken)

            userId
        }
    }

    override suspend fun clearUser() {
        dataStore.edit { pref ->
            val userId = pref[UserKey.userId] ?: return@edit

            pref.clear()
            pref[UserKey.userId] = userId
        }
    }

    override suspend fun updateUser(user: User) {
        dataStore.edit { pref ->
            pref[UserKey.userId] = user.userId
            pref[UserKey.universityId] = user.universityId
            pref[UserKey.universityName] = user.universityName
            pref[UserKey.departmentId] = user.departmentId
            pref[UserKey.departmentName] = user.departmentName
            pref[UserKey.fcmToken] = user.fcmToken
            pref[UserKey.isPushNotificationAllowed] = user.isPushNotificationAllowed
        }
    }
}
