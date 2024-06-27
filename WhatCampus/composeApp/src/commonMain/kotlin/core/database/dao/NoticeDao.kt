package core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import core.database.entity.NoticeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticeDao {
    @Query("SELECT * FROM notice ORDER BY createdAt DESC")
    fun getAll(): Flow<List<NoticeEntity>>

    @Upsert
    suspend fun upsert(notice: NoticeEntity)

    @Delete
    suspend fun delete(notice: NoticeEntity)
}