package core.common.util

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class UrlSharer {
    actual fun shareUrl(url: String) {
        val items = listOf(url)
        val activityViewController = UIActivityViewController(items, null)
        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

        rootViewController?.presentViewController(activityViewController, animated = true, completion = null)
    }
}
