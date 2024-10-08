package core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import core.database.WhatcamDatabase.Companion.DATABASE_VERSION
import core.database.converter.LocalDateTimeConverter
import core.database.dao.NoticeDao
import core.database.dao.SearchQueryDao
import core.database.entity.NoticeEntity
import core.database.entity.SearchQueryEntity

@Database(
    entities = [
        NoticeEntity::class,
        SearchQueryEntity::class,
    ],
    version = DATABASE_VERSION,
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class WhatcamDatabase : RoomDatabase(), DB {

    abstract fun noticeDao(): NoticeDao
    abstract fun searchQueryDao(): SearchQueryDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

    companion object {
        internal const val DATABASE_NAME = "whatcam.db"
        internal const val DATABASE_VERSION = 1
    }
}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}
