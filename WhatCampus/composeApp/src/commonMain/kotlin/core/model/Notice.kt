package core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val id: Long,
    val title: String,
    val datetime: LocalDateTime,
    val url: String,
)
