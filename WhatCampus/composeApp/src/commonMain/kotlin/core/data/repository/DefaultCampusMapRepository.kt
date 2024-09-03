package core.data.repository

import core.data.common.safeGet
import core.data.model.CampusMapResponse
import core.domain.repository.CampusMapRepository
import core.model.Response
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultCampusMapRepository(
    private val httpClient: HttpClient,
) : CampusMapRepository {

    override fun flowCampusMapUrl(
        campusId: Long,
    ): Flow<Response<String>> = flow {
        val requestUrl = "/api/v1/campuses/$campusId/map"
        val campusMapResponse = httpClient.safeGet<CampusMapResponse>(requestUrl)

        emit(campusMapResponse.map(CampusMapResponse::campusMapUrl))
    }
}
