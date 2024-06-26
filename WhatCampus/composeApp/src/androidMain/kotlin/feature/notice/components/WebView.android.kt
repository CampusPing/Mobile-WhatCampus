package feature.notice.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun WebView(
    modifier: Modifier,
    url: String,
) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
            }
        },
        modifier = modifier,
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()
            webView.setNetworkAvailable(true)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)
        }
    )
}
