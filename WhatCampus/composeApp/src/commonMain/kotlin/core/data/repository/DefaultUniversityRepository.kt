package core.data.repository

import core.data.common.safeGet
import core.data.mapper.toUniversities
import core.data.model.CampusesResponse
import core.domain.repository.UniversityRepository
import core.model.Response
import core.model.University
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultUniversityRepository(
    private val httpClient: HttpClient,
) : UniversityRepository {
    private val universities = mutableListOf<University>()

    override fun flowUniversity(
        query: String,
    ): Flow<Response<List<University>>> {
        if (universities.isNotEmpty()) {
            return flow {
                emit(Response.Success(universities.filter(query)))
            }
        }

        return flow {
            val campusesResponse = httpClient
                .safeGet<CampusesResponse>("/api/v1/campuses")
                .map(CampusesResponse::toUniversities)
            val filteredCampusesByQuery = campusesResponse.map { it.filter(query) }

            emit(filteredCampusesByQuery)

            if (campusesResponse is Response.Success) {
                universities.clear()
                universities.addAll(campusesResponse.body)
            }
        }
    }

    private fun List<University>.filter(
        query: String,
    ): List<University> = this
        .filter { university -> query in university.name }
        .ifEmpty { this }
}
