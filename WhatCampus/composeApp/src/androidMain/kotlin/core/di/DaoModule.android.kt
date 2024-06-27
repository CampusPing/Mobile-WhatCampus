package core.di

import core.database.getWhatcamDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val daoModule: Module = module {
    single {
        getWhatcamDatabase(context = androidContext()).noticeDao()
    }
}
