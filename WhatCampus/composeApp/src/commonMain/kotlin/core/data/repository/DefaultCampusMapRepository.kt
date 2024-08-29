package core.data.repository

import core.data.model.CampusMapResponse
import core.domain.repository.CampusMapRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultCampusMapRepository(
    private val httpClient: HttpClient,
) : CampusMapRepository {
    override fun flowCampusMapUrl(
        campusId: Long,
    ): Flow<String> = flow {
        val requestUrl = "/api/v1/campuses/$campusId/map"
        val campusMapResponse = httpClient.get(requestUrl).body<CampusMapResponse>()

        emit(campusMapResponse.campusMapUrl)
    }
}
