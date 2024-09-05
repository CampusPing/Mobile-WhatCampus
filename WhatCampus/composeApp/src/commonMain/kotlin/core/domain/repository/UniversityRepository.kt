package core.domain.repository

import core.model.Response
import core.model.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun flowUniversity(query: String): Flow<Response<List<University>>>
}
