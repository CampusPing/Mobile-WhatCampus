package core.common.extensions

import io.ktor.util.decodeBase64String
import io.ktor.util.encodeBase64

fun String.encodeBase64UrlSafe(): String = this
    .encodeBase64()
    .replace('/', '_')

fun String.decodeBase64UrlSafe(): String = this
    .replace('_', '/')
    .decodeBase64String()
