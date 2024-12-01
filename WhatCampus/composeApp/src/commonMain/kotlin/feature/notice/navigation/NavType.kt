package feature.notice.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import core.common.extensions.decodeBase64UrlSafe
import core.common.extensions.encodeBase64UrlSafe
import core.model.Notice
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal val NoticeNavType = object : NavType<Notice>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): Notice? {
        return Json.decodeFromString(bundle.getString(key)?.decodeBase64UrlSafe() ?: return null)
    }

    override fun parseValue(value: String): Notice {
        return Json.decodeFromString(value.decodeBase64UrlSafe())
    }

    override fun put(bundle: Bundle, key: String, value: Notice) {
        bundle.putString(key, Json.encodeToString(value).encodeBase64UrlSafe())
    }

    override fun serializeAsValue(value: Notice): String {
        return Json.encodeToString(value).encodeBase64UrlSafe()
    }
}
