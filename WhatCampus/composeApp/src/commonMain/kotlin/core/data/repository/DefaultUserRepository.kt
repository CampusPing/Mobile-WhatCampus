package core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.mmk.kmpnotifier.notification.NotifierManager
import core.data.common.safePost
import core.data.model.MemberRegisterRequest
import core.data.model.MemberRegisterResponse
import core.datastore.key.UserKey
import core.domain.repository.TokenRepository
import core.domain.repository.UserRepository
import core.model.Response
import core.model.User
import io.ktor.client.HttpClient
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DefaultUserRepository(
    private val httpClient: HttpClient,
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
            val fcmToken = tokenRepository.getFcmToken().firstOrNull() ?: ""

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

    @OptIn(InternalAPI::class)
    override suspend fun createUser(
        universityId: Long,
        universityName: String,
        departmentId: Long,
        departmentName: String,
        noticeCategoryIds: List<Long>,
    ): Response<Long> {
        val fcmToken = NotifierManager.getPushNotifier().getToken() ?: throw Error("토큰을 불러올 수 없습니다.")
        tokenRepository.saveFcmToken(fcmToken)

        val userRegisterResponse = httpClient.safePost<MemberRegisterResponse>("/api/v1/members") {
            body = Json.encodeToString(
                MemberRegisterRequest(
                    fcmToken = fcmToken,
                    campusId = universityId,
                    departmentId = departmentId,
                    categoryIds = noticeCategoryIds,
                )
            )
        }

        if (userRegisterResponse is Response.Success) {
            clearUser()
            dataStore.edit { pref ->
                pref[UserKey.userId] = userRegisterResponse.body.memberId
                pref[UserKey.universityId] = universityId
                pref[UserKey.universityName] = universityName
                pref[UserKey.departmentId] = departmentId
                pref[UserKey.departmentName] = departmentName
                pref[UserKey.isPushNotificationAllowed] = true
            }
        }

        return userRegisterResponse.map(MemberRegisterResponse::memberId)
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
