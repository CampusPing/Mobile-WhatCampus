package core.domain.repository

import core.model.Response
import kotlinx.coroutines.flow.Flow

interface CampusMapRepository {
    fun flowCampusMapUrl(campusId: Long): Flow<Response<String>>
}
