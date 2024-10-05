package core.common.util

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.analytics.FirebaseAnalyticsEvents
import dev.gitlive.firebase.analytics.FirebaseAnalyticsParam
import dev.gitlive.firebase.analytics.analytics

fun logFirebaseAnalyticsEvent(
    name: String,
    parameters: Map<String, Any> = emptyMap(),
) {
    Firebase.analytics.logEvent(
        name = name,
        parameters = parameters,
    )
}

fun logAppPlatformEvent() {
    logFirebaseAnalyticsEvent(
        name = FirebaseAnalyticsEvents.APP_OPEN,
        parameters = mapOf(FirebaseAnalyticsParam.SOURCE_PLATFORM to platform)
    )
}

fun logScreenEvent(screenName: String) {
    logFirebaseAnalyticsEvent(
        name = FirebaseAnalyticsEvents.SCREEN_VIEW,
        parameters = mapOf(FirebaseAnalyticsParam.SCREEN_NAME to screenName)
    )
}

expect val platform: String
