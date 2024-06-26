package core.di

import core.domain.usecase.GetAllNoticeCategoryUseCase
import core.domain.usecase.GetAllNoticeUseCase
import core.domain.usecase.GetUniversityUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single {
        GetUniversityUseCase(get())
    }
    single {
        GetAllNoticeCategoryUseCase(get())
    }
    single {
        GetAllNoticeUseCase(get())
    }
}
