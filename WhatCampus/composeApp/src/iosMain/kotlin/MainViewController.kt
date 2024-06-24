import androidx.compose.ui.window.ComposeUIViewController
import core.di.KoinInitializer
import feature.app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}
