package core.di

import core.domain.usecase.BookmarkNoticeUseCase
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.GetCampusMapUrlUseCase
import core.domain.usecase.GetNoticeCategoriesByUniversityIdUseCase
import core.domain.usecase.GetNoticesByCategoryIdUseCase
import core.domain.usecase.GetSubscribedNoticeCategoriesUseCase
import core.domain.usecase.GetUniversityUseCase
import core.domain.usecase.IsBookmarkedNoticeUseCase
import core.domain.usecase.SearchNoticesUseCase
import core.domain.usecase.SubscribeNoticeCategoriesUseCase
import core.domain.usecase.UnbookmarkNoticeUseCase
import core.domain.usecase.UnbookmarkNoticesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single {
        GetUniversityUseCase(get())
    }
    single {
        GetNoticeCategoriesByUniversityIdUseCase(get())
    }
    single {
        GetNoticesByCategoryIdUseCase(get())
    }
    single {
        GetAllBookmarkedNoticesUseCase(get())
    }
    single {
        IsBookmarkedNoticeUseCase(get())
    }
    single {
        BookmarkNoticeUseCase(get())
    }
    single {
        UnbookmarkNoticeUseCase(get())
    }
    single {
        UnbookmarkNoticesUseCase(get())
    }
    single {
        GetCampusMapUrlUseCase(get())
    }
    single {
        SearchNoticesUseCase(get(), get())
    }
    single {
        SubscribeNoticeCategoriesUseCase(get())
    }
    single {
        GetSubscribedNoticeCategoriesUseCase(get())
    }
}
