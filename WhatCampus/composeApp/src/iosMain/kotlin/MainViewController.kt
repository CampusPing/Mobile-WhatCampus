import androidx.compose.ui.window.ComposeUIViewController
import core.di.KoinInitializer
import feature.app.App
import feature.app.KmpInitializer
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    KmpInitializer.init()
    Napier.base(DebugAntilog())
    App()
}
