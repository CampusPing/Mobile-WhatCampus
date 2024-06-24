package core.domain.usecase

import core.domain.repository.UniversityRepository
import core.model.University
import kotlinx.coroutines.flow.Flow

data class GetUniversityUseCase(
    private val repository: UniversityRepository,
) {
    operator fun invoke(): Flow<List<University>> =
        repository.flowUniversity()
}