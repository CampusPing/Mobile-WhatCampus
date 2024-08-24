package core.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveFcmToken(fcmToken: String)
    fun getFcmToken(): Flow<String?>
}
