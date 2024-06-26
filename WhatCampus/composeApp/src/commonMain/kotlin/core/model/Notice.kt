package core.model

import kotlinx.datetime.LocalDateTime

data class Notice(
    val id: Int,
    val title: String,
    val datetime: LocalDateTime,
    val url: String,
)
