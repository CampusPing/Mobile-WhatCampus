package core.di

import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.GetFilteredNoticesUseCase
import core.domain.usecase.GetNoticesByCategoryIdUseCase
import core.domain.usecase.GetNoticesByDepartmentIdUseCase
import core.domain.usecase.IsBookmarkedNoticeUseCase
import core.domain.usecase.SubscribeNoticeCategoriesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single {
        GetNoticesByCategoryIdUseCase(get())
    }
    single {
        GetNoticesByDepartmentIdUseCase(get())
    }
    single {
        GetAllBookmarkedNoticesUseCase(get())
    }
    single {
        IsBookmarkedNoticeUseCase(get())
    }
    single {
        GetFilteredNoticesUseCase(get(), get(), get())
    }
    single {
        SubscribeNoticeCategoriesUseCase(get())
    }
}
