package core.domain.repository

import core.model.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun flowUniversity(): Flow<List<University>>
}
