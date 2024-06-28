package core.domain.repository

import kotlinx.coroutines.flow.Flow

interface CampusMapRepository {
    fun flowCampusMapUrl(campusId: Long): Flow<String>
}
