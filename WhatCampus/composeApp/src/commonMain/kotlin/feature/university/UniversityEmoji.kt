package feature.university

import core.domain.University

internal enum class UniversityEmoji(
    val emoji: String,
) {
    SANGMYUNG("\uD83E\uDECE"),
}

internal fun University.toEmoji(): String = when (this.id) {
    0L, 1L -> UniversityEmoji.SANGMYUNG.emoji
    else -> ""
}
