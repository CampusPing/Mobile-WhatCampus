package core.data.repository

import core.data.mapper.toUniversities
import core.data.model.CampusesResponse
import core.domain.repository.UniversityRepository
import core.model.University
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultUniversityRepository(
    private val httpClient: HttpClient,
) : UniversityRepository {
    override fun flowUniversity(query: String): Flow<List<University>> {
        return flow {
            val campusesResponse = httpClient.get("/api/v1/campuses").body<CampusesResponse>()
            emit(campusesResponse.toUniversities())
        }
    }
}
