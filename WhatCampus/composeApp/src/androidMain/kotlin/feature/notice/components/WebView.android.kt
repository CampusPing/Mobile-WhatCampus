package feature.notice.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import core.designsystem.components.LoadingScreen

@Composable
actual fun WebView(
    modifier: Modifier,
    url: String,
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        factory = { createWebView(context) { isLoading = it } },
        update = { webView -> webView.loadUrl(url) }
    )

    if (isLoading) {
        LoadingScreen()
    }
}

@SuppressLint("SetJavaScriptEnabled")
private fun createWebView(
    context: Context,
    onLoadingStateChanged: (Boolean) -> Unit,
): WebView = WebView(context).apply {
    with(settings) {
        builtInZoomControls = false
        domStorageEnabled = true
        javaScriptEnabled = true
        loadWithOverviewMode = true
        blockNetworkLoads = false
        setSupportZoom(false)
    }
    webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            onLoadingStateChanged(true)
        }

        override fun onPageFinished(webView: WebView, url: String) {
            super.onPageFinished(webView, url)
            onLoadingStateChanged(false)
            webView.removeHeaderAndFooter()
        }
    }
    WebView.setWebContentsDebuggingEnabled(true)
}

private fun WebView.removeHeaderAndFooter() {
    postDelayed({
        loadUrl(
            "javascript:(function() { " +
                    "console.log('Removing headers and footers');" +
                    "var headers = document.querySelectorAll('header');" +
                    "headers.forEach(function(header) { if (header && header.parentNode) { header.parentNode.removeChild(header); } });" +
                    "var footers = document.querySelectorAll('footer');" +
                    "footers.forEach(function(footer) { if (footer && footer.parentNode) { footer.parentNode.removeChild(footer); } });" +
                    "})()"
        )
    }, 10)
}
