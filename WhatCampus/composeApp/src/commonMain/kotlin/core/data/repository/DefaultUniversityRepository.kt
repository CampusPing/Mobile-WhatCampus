package core.data.repository

import core.domain.repository.UniversityRepository
import core.model.University
import kotlinx.coroutines.flow.Flow

class DefaultUniversityRepository : UniversityRepository {
    override fun flowUniversity(query: String): Flow<List<University>> {
        TODO("Not yet implemented")
    }
}
