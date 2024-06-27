package core.di

import core.domain.usecase.BookmarkNoticeUseCase
import core.domain.usecase.GetAllBookmarkedNoticesUseCase
import core.domain.usecase.GetAllNoticeCategoryUseCase
import core.domain.usecase.GetAllNoticeUseCase
import core.domain.usecase.GetUniversityUseCase
import core.domain.usecase.IsBookmarkedNoticeUseCase
import core.domain.usecase.UnbookmarkNoticeUseCase
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
}
