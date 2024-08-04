package core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.datastore.factory.DATA_STORE_FILE_NAME
import core.datastore.factory.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module = module {
    single<DataStore<Preferences>> {
        createDataStore {
            androidContext().filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
        }
    }
}
