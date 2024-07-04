package core.datastore.key

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object UserKey {
    val userId = longPreferencesKey("userId")
    val universityId = longPreferencesKey("universityId")
    val universityName = stringPreferencesKey("universityName")
    val departmentId = longPreferencesKey("departmentId")
    val departmentName = stringPreferencesKey("userId")
    val fcmToken = stringPreferencesKey("fcmToken")
}
