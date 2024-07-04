package feature.app

import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration

actual fun initKmpNotifierPlatformSpecific() {
    NotifierManager.initialize(
        NotificationPlatformConfiguration.Ios(
            showPushNotification = true,
            askNotificationPermissionOnStart = true
        )
    )
}
