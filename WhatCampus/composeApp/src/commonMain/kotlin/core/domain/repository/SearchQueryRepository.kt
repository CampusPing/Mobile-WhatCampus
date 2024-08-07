package core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchQueryRepository {
    fun flowSearchQueryHistories(): Flow<List<String>>

    suspend fun addSearchQueryHistory(query: String): Long

    suspend fun deleteQueryHistories(query: String? = null)
}
