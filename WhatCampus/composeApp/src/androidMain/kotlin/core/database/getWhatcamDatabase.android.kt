package core.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getWhatcamDatabase(context: Context): WhatcamDatabase {
    val dbFile = context.getDatabasePath(WhatcamDatabase.DATABASE_NAME)

    return Room.databaseBuilder<WhatcamDatabase>(
        context = context,
        name = dbFile.absolutePath,
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
