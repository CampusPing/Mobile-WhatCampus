package core.domain.repository

import core.model.Response
import core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun flowUser(): Flow<User?>

    suspend fun createUser(
        universityId: Long,
        universityName: String,
        departmentId: Long,
        departmentName: String,
        noticeCategoryIds: List<Long>,
    ): Response<Long>

    suspend fun clearUser()
    suspend fun updateUser(user: User)
}
