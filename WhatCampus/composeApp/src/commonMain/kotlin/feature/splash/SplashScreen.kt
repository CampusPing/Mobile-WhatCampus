package feature.splash

import androidx.compose.runtime.Composable
import core.di.koinViewModel

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onSplashDone: (shouldOnboarding: Boolean) -> Unit,
) {

}
