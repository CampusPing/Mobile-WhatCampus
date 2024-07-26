package core.di

import core.database.WhatcamDatabase
import core.database.getWhatcamDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val daoModule: Module = module {
    single { getWhatcamDatabase(androidContext()) }
    single { get<WhatcamDatabase>().noticeDao() }
    single { get<WhatcamDatabase>().searchQueryDao() }
}
