import SwiftUI
import ComposeApp
import FirebaseCore
import FirebaseMessaging
import UserNotifications

class AppDelegate: NSObject, UIApplicationDelegate, UNUserNotificationCenterDelegate {

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        NotifierInitializer.shared.onApplicationStart()

        let center = UNUserNotificationCenter.current()
        center.delegate = self

        return true
    }

    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        Messaging.messaging().apnsToken = deviceToken
    }
    
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable : Any]) async -> UIBackgroundFetchResult {
        NotifierManager.shared.onApplicationDidReceiveRemoteNotification(userInfo: userInfo)
        return UIBackgroundFetchResult.newData
    }

    func userNotificationCenter(_ center: UNUserNotificationCenter,
                                didReceive response: UNNotificationResponse,
                                withCompletionHandler completionHandler: @escaping () -> Void) {
        var updatedUserInfo = response.notification.request.content.userInfo
        updatedUserInfo["isBackground"] = true

        let newContent = response.notification.request.content.mutableCopy() as! UNMutableNotificationContent
        newContent.userInfo = updatedUserInfo

        NotifierManager.shared.onApplicationDidReceiveRemoteNotification(userInfo: updatedUserInfo)
        completionHandler()
    }

}

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
