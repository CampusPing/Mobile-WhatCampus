package core.model

import kotlinx.datetime.LocalDateTime

data class Notice(
    val id: Long,
    val title: String,
    val datetime: LocalDateTime,
    val url: String,
)
