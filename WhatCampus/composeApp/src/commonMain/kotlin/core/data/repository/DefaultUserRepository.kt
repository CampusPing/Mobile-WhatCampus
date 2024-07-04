package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.datastore.key.UserKey
import core.domain.repository.UserRepository
import core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultUserRepository(
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
            )
        }

    override suspend fun saveUser(user: User) {
        dataStore.edit { pref ->
            pref[UserKey.userId] = user.userId
            pref[UserKey.universityId] = user.universityId
            pref[UserKey.universityName] = user.universityName
            pref[UserKey.departmentId] = user.departmentId
            pref[UserKey.departmentName] = user.departmentName
            pref[UserKey.fcmToken] = user.fcmToken
        }
    }
}
