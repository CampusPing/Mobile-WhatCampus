package core.di

import core.database.WhatcamDatabase
import core.database.getWhatcamDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val daoModule: Module = module {
    single { getWhatcamDatabase() }
    single { get<WhatcamDatabase>().noticeDao() }
    single { get<WhatcamDatabase>().searchQueryDao() }
    single { get<WhatcamDatabase>().notificationDao() }
}
