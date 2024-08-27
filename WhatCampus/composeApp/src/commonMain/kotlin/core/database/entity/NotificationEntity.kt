package core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import core.database.entity.NotificationEntity.Companion.TABLE_NAME
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = TABLE_NAME)
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = ANY_ID,
    val content: String,
    val isRead: Boolean = false,
    val receivedDatetime: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val noticeId: Long,
    val noticeTitle: String,
    val noticeDatetime: LocalDateTime,
    val noticeUrl: String,
) {

    companion object {
        const val TABLE_NAME = "notification"
        private const val ANY_ID = 0L
    }
}
