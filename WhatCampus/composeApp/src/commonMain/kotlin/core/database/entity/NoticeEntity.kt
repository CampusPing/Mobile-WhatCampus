package core.database.entity

import androidx.room.Entity
import core.database.entity.NoticeEntity.Companion.TABLE_NAME
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["id", "url"],
)
data class NoticeEntity(
    val id: Long,
    val title: String,
    val datetime: LocalDateTime,
    val url: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
) {

    companion object {
        const val TABLE_NAME = "notice"
    }
}
