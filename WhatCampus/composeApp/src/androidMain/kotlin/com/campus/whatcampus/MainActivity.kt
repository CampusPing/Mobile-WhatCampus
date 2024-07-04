package com.campus.whatcampus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mmk.kmpnotifier.extensions.onCreateOrOnNewIntent
import com.mmk.kmpnotifier.notification.NotifierManager
import feature.app.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotifierManager.onCreateOrOnNewIntent(intent)
        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        NotifierManager.onCreateOrOnNewIntent(intent)
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
