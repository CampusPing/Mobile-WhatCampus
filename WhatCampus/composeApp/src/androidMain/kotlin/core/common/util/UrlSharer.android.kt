package core.common.util

import android.content.Context
import android.content.Intent

actual class UrlSharer(
    private val context: Context,
) {
    actual fun shareUrl(url: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND_MULTIPLE
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }

        context.startActivity(
            Intent
                .createChooser(shareIntent, null)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
