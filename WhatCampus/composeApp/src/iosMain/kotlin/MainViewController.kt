import androidx.compose.ui.window.ComposeUIViewController
import core.di.KoinInitializer
import feature.app.App
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun MainViewController() = ComposeUIViewController {
    KoinInitializer().init()
    Napier.base(DebugAntilog())
    App()
}
