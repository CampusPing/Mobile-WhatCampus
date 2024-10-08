package core.di

import feature.bookmark.BookmarkViewModel
import feature.campusmap.CampusMapViewModel
import feature.notice.NoticeDetailViewModel
import feature.notice.NoticeViewModel
import feature.noticeSearch.NoticeSearchViewModel
import feature.notificationArchive.NotificationArchiveViewModel
import feature.profile.ProfileViewModel
import feature.profile.subscreen.noticeCategory.NoticeCategoryViewModel
import feature.splash.SplashViewModel
import feature.university.UniversityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::UniversityViewModel)
    viewModelOf(::NoticeViewModel)
    viewModelOf(::NoticeDetailViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::CampusMapViewModel)
    viewModelOf(::NoticeSearchViewModel)
    viewModelOf(::NotificationArchiveViewModel)
    viewModelOf(::NoticeCategoryViewModel)
    viewModelOf(::ProfileViewModel)
}
