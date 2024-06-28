package core.domain.usecase

import core.domain.repository.CampusMapRepository
import kotlinx.coroutines.flow.Flow

data class GetCampusMapUrlUseCase(
    private val repository: CampusMapRepository,
) {
    operator fun invoke(campusId: Long): Flow<String> {
        return repository.flowCampusMapUrl(campusId = campusId)
    }
}
