@file:OptIn(FormatStringsInDatetimeFormats::class)

package feature.notice

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

internal fun LocalDateTime.format(
    formatter: DateTimeFormat<LocalDateTime> = noticeDatetimeFormatter,
): String = format(formatter)

internal fun String.parse(
    formatter: DateTimeFormat<LocalDateTime> = noticeDatetimeFormatter,
): LocalDateTime = LocalDateTime.parse(this, formatter)

internal val noticeDatetimeFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy/MM/dd HH:mm:ss")
}

internal val noticeDateFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy/MM/dd")
}
