@file:OptIn(FormatStringsInDatetimeFormats::class)

package core.common.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

internal fun LocalDateTime.format(
    formatter: DateTimeFormat<LocalDateTime> = defaultDatetimeFormatter,
): String = format(formatter)

internal fun String.parse(
    formatter: DateTimeFormat<LocalDateTime> = defaultDateFormatter,
): LocalDateTime = LocalDateTime.parse(this, formatter)

internal val defaultDatetimeFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy/MM/dd HH:mm:ss")
}

internal val defaultDateFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern("yyyy/MM/dd")
}
