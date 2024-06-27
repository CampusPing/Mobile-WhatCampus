package core.di

import feature.notice.NoticeDetailViewModel
import feature.notice.NoticeViewModel
import feature.university.UniversityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    singleOf(::UniversityViewModel)
    singleOf(::NoticeViewModel)
    singleOf(::NoticeDetailViewModel)
}
