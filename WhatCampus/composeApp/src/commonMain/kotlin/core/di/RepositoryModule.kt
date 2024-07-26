package core.di

import core.data.repository.DefaultSearchQueryRepository
import core.data.repository.FakeCampusMapRepository
import core.data.repository.FakeNoticeRepository
import core.data.repository.FakeUniversityRepository
import core.domain.repository.CampusMapRepository
import core.domain.repository.NoticeRepository
import core.domain.repository.SearchQueryRepository
import core.domain.repository.UniversityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UniversityRepository> {
        FakeUniversityRepository()
    }
    single<NoticeRepository> {
        FakeNoticeRepository(get())
    }
    single<CampusMapRepository> {
        FakeCampusMapRepository()
    }
    single<SearchQueryRepository> {
        DefaultSearchQueryRepository(get())
    }
}
