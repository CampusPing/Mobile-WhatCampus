package core.data.repository

import core.database.dao.SearchQueryDao
import core.database.entity.SearchQueryEntity
import core.domain.repository.SearchQueryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultSearchQueryRepository(
    private val searchQueryDao: SearchQueryDao,
) : SearchQueryRepository {
    override fun flowSearchQueryHistories(): Flow<List<String>> = searchQueryDao
        .getAll()
        .map { searchQueries -> searchQueries.distinctBy { entity -> entity.searchQuery } }
        .map { searchQueries -> searchQueries.map(SearchQueryEntity::searchQuery) }

    override suspend fun addSearchQueryHistory(query: String): Long {
        val searchQueryEntity = SearchQueryEntity(searchQuery = query)
        return searchQueryDao.insert(searchQuery = searchQueryEntity)
    }

    override suspend fun deleteQueryHistories(query: String?) {
        if (query == null) {
            searchQueryDao.deleteAll()
        } else {
            searchQueryDao.deleteByQuery(searchQuery = query)
        }
    }
}
