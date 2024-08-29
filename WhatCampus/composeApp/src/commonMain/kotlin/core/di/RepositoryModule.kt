package core.di

import core.data.repository.DefaultCampusMapRepository
import core.data.repository.DefaultNoticeRepository
import core.data.repository.DefaultNotificationRepository
import core.data.repository.DefaultSearchQueryRepository
import core.data.repository.DefaultTokenRepository
import core.data.repository.DefaultUniversityRepository
import core.data.repository.DefaultUserRepository
import core.domain.repository.CampusMapRepository
import core.domain.repository.NoticeRepository
import core.domain.repository.NotificationRepository
import core.domain.repository.SearchQueryRepository
import core.domain.repository.TokenRepository
import core.domain.repository.UniversityRepository
import core.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UniversityRepository> {
        DefaultUniversityRepository(get())
    }
    single<NoticeRepository> {
        DefaultNoticeRepository(get(), get())
    }
    single<CampusMapRepository> {
        DefaultCampusMapRepository(get())
    }
    single<UserRepository> {
        DefaultUserRepository(get(), get(), get())
    }
    single<TokenRepository> {
        DefaultTokenRepository(get())
    }
    single<SearchQueryRepository> {
        DefaultSearchQueryRepository(get())
    }
    single<NotificationRepository> {
        DefaultNotificationRepository(get(), get())
    }
}
