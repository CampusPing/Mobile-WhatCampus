package core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun getWhatcamDatabase(): WhatcamDatabase {
    val docDirectoryUrl = NSFileManager
        .defaultManager()
        .URLForDirectory(NSDocumentDirectory, NSUserDomainMask, null, true, null)
    val dbFile = "${docDirectoryUrl?.path}/${WhatcamDatabase.DATABASE_NAME}"

    return Room.databaseBuilder<WhatcamDatabase>(
        name = dbFile,
        factory = { WhatcamDatabase::class.instantiateImpl() }
    )
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
