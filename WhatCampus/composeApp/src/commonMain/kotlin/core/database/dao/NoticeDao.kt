package core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.NoticeEntity
import core.database.entity.NoticeEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticeDao {
    @Query("SELECT * FROM $TABLE_NAME ORDER BY createdAt DESC")
    fun getAll(): Flow<List<NoticeEntity>>

    @Insert
    suspend fun insert(notice: NoticeEntity)

    @Delete
    suspend fun delete(notice: NoticeEntity)
}
