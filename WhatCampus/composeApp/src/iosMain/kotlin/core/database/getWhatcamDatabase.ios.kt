package core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

fun getWhatcamDatabase(): WhatcamDatabase {
    val dbFile = "${NSHomeDirectory()}/${WhatcamDatabase.DATABASE_NAME}"

    return Room.databaseBuilder<WhatcamDatabase>(
        name = dbFile,
        factory = { WhatcamDatabase::class.instantiateImpl() }
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
