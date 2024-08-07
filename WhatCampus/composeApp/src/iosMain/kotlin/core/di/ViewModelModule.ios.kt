package core.di

import feature.bookmark.BookmarkViewModel
import feature.campusmap.CampusMapViewModel
import feature.notice.NoticeDetailViewModel
import feature.notice.NoticeViewModel
import feature.profile.subscreen.noticeCategory.NoticeCategoryViewModel
import feature.noticeSearch.NoticeSearchViewModel
import feature.profile.ProfileViewModel
import feature.splash.SplashViewModel
import feature.university.UniversityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    factoryOf(::SplashViewModel)
    singleOf(::UniversityViewModel)
    singleOf(::NoticeViewModel)
    factoryOf(::NoticeDetailViewModel)
    singleOf(::BookmarkViewModel)
    singleOf(::CampusMapViewModel)
    factoryOf(::NoticeSearchViewModel)
    factoryOf(::NoticeCategoryViewModel)
    factoryOf(::ProfileViewModel)
}
