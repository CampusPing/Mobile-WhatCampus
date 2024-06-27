package core.di

import core.database.getWhatcamDatabase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val daoModule: Module = module {
    singleOf(getWhatcamDatabase()::noticeDao)
}
