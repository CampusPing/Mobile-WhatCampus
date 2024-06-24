package core.di

import core.data.repository.FakeUniversityRepository
import core.domain.repository.UniversityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UniversityRepository> {
        FakeUniversityRepository()
    }
}
