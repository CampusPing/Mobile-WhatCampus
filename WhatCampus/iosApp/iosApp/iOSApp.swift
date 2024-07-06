import SwiftUI
import Firebase

@main
struct iOSApp: App {
    init() {
        print("iOSApp: Initializing Firebase")
        FirebaseApp.configure()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
