package core.di

import core.data.repository.FakeNoticeRepository
import core.data.repository.FakeUniversityRepository
import core.domain.repository.NoticeRepository
import core.domain.repository.UniversityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UniversityRepository> {
        FakeUniversityRepository()
    }
    single<NoticeRepository> {
        FakeNoticeRepository()
    }
}
