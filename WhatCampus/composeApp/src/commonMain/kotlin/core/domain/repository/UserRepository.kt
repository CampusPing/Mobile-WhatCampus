package core.domain.repository

import core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun flowUser(): Flow<User?>

    fun createUser(
        universityId: Long,
        universityName: String,
        departmentId: Long,
        departmentName: String,
    ): Flow<Long>

    suspend fun clearUser()
    suspend fun updateUser(user: User)
}
