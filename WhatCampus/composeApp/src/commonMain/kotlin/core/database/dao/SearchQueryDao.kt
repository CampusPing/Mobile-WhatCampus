package core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.SearchQueryEntity
import core.database.entity.SearchQueryEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC")
    fun getAll(): Flow<List<SearchQueryEntity>>

    @Insert
    suspend fun insert(searchQuery: SearchQueryEntity): Long

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME WHERE searchQuery = :searchQuery")
    suspend fun deleteByQuery(searchQuery: String)
}
