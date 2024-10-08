package core.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                viewModelModule,
                repositoryModule,
                usecaseModule,
                daoModule,
                utilModule,
                dataStoreModule,
                networkModule,
            )
        }
    }
}
