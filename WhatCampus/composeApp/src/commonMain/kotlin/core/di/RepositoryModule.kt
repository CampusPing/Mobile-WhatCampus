package core.di

import core.data.repository.DefaultSearchQueryRepository
import core.data.repository.DefaultTokenRepository
import core.data.repository.DefaultUserRepository
import core.data.repository.FakeCampusMapRepository
import core.data.repository.FakeNoticeRepository
import core.data.repository.FakeUniversityRepository
import core.domain.repository.CampusMapRepository
import core.domain.repository.NoticeRepository
import core.domain.repository.SearchQueryRepository
import core.domain.repository.TokenRepository
import core.domain.repository.UniversityRepository
import core.domain.repository.UserRepository
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
    single<UserRepository> {
        DefaultUserRepository(get(), get())
    }
    single<TokenRepository> {
        DefaultTokenRepository(get())
    }
    single<SearchQueryRepository> {
        DefaultSearchQueryRepository(get())
    }
}
