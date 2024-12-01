package core.designsystem.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(
    modifier: Modifier,
    url: String,
) {
    val webView = remember { WKWebView() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(url) {
        val request = NSMutableURLRequest.requestWithURL(URL = NSURL(string = url))
        webView.loadRequest(request)
    }

    val navigationDelegate = remember {
        object : NSObject(), WKNavigationDelegateProtocol {

            override fun webView(webView: WKWebView, didFinishNavigation: WKNavigation?) {
                isLoading = false
                webView.removeHeaderAndFooter()
            }
        }
    }
    webView.navigationDelegate = navigationDelegate

    UIKitView(
        factory = { webView },
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    )

    if (isLoading) {
        LoadingScreen()
    }
}

private fun WKWebView.removeHeaderAndFooter() {
    evaluateJavaScript(
        "document.querySelectorAll('header').forEach(header => header.parentNode.removeChild(header));" +
                "document.querySelectorAll('footer').forEach(footer => footer.parentNode.removeChild(footer));",
        null
    )
}
