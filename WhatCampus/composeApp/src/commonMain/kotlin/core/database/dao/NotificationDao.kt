package core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.NotificationEntity
import core.database.entity.NotificationEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM $TABLE_NAME ORDER BY receivedDatetime DESC")
    fun getAll(): Flow<List<NotificationEntity>>

    @Insert
    suspend fun insert(notification: NotificationEntity)

    @Query("UPDATE $TABLE_NAME SET isRead = 1 WHERE id = :id")
    suspend fun updateIsRead(id: Long)

    @Delete
    suspend fun delete(notification: NotificationEntity)
}
