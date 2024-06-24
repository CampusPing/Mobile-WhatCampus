package core.data.repository

import core.domain.repository.UniversityRepository
import core.model.University
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUniversityRepository : UniversityRepository {
    private val universities = listOf(
        University(
            id = 0L,
            name = "상명대학교 (서울)",
            departments = persistentListOf()
        ),
        University(
            id = 1L,
            name = "상명대학교 (천안)",
            departments = persistentListOf()
        ),
        University(
            id = 2L,
            name = "동양미래대학교",
            departments = persistentListOf()
        ),
        University(
            id = 3L,
            name = "상명대학교 (서울)",
            departments = persistentListOf()
        ),
        University(
            id = 4L,
            name = "상명대학교 (서울)",
            departments = persistentListOf()
        ),
        University(
            id = 5L,
            name = "상명대학교 (서울)",
            departments = persistentListOf()
        ),
        University(
            id = 6L,
            name = "상명대학교 (서울)",
            departments = persistentListOf()
        ),
    )

    override fun flowUniversity(): Flow<List<University>> {
        return flow {
            emit(universities)
        }
    }
}
