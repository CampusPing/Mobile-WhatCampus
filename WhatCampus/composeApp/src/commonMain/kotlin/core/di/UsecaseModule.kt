package core.di

import core.domain.usecase.GetUniversityUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single {
        GetUniversityUseCase(get())
    }
}
