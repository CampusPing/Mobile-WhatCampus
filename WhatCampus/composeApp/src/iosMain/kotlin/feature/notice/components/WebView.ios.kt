package feature.notice.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(
    modifier: Modifier,
    url: String,
) {
    val webView = remember { WKWebView() }
    val request = NSMutableURLRequest.requestWithURL(URL = NSURL(string = url))

    webView.apply {
        loadRequest(request)
        allowsBackForwardNavigationGestures = true
    }

    UIKitView(
        factory = { webView },
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    )
}
