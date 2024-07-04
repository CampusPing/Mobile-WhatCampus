package feature.app

import com.campus.whatcampus.R
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration

actual fun initKmpNotifierPlatformSpecific() {
    NotifierManager.initialize(
        configuration = NotificationPlatformConfiguration.Android(
            notificationIconResId = R.drawable.ic_foreground,
            showPushNotification = true,
        )
    )
}
