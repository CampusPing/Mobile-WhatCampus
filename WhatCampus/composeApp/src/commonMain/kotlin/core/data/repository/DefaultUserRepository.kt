package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.datastore.key.UserKey
import core.domain.repository.UserRepository
import core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultUserRepository(
    // private val ktorClient: KtorClient,
    private val dataStore: DataStore<Preferences>,
) : UserRepository {

    override fun flowUser(): Flow<User> = dataStore.data
        .map { pref ->
            User(
                userId = requireNotNull(pref[UserKey.userId]),
                universityId = requireNotNull(pref[UserKey.universityId]),
                universityName = requireNotNull(pref[UserKey.universityName]),
                departmentId = requireNotNull(pref[UserKey.departmentId]),
                departmentName = requireNotNull(pref[UserKey.departmentName]),
                fcmToken = requireNotNull(pref[UserKey.fcmToken]),
                isPushNotificationAllowed = requireNotNull(pref[UserKey.isPushNotificationAllowed]),
            )
        }

    override fun createUser(
        universityId: Long,
        universityName: String,
        departmentId: Long,
        departmentName: String,
        fcmToken: String,
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
                pref[UserKey.fcmToken] = fcmToken
                pref[UserKey.isPushNotificationAllowed] = true
            }

            userId
        }
    }
}
