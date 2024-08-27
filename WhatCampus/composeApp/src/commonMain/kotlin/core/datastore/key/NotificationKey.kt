package core.datastore.key

import androidx.datastore.preferences.core.booleanPreferencesKey

object NotificationKey {
    val hasNewNotification = booleanPreferencesKey("hasNewNotification")
}
