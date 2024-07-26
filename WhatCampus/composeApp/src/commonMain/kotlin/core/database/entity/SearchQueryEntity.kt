package core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import core.database.entity.SearchQueryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SearchQueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = ANY_ID,
    val searchQuery: String,
) {

    companion object {
        const val TABLE_NAME = "search_query"
        private const val ANY_ID = 0L
    }
}
