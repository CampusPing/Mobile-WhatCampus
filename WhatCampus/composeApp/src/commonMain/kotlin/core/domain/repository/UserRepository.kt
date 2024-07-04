package core.domain.repository

import core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun flowUser(): Flow<User>
    suspend fun saveUser(user: User)
}
