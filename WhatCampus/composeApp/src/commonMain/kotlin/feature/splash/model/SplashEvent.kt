package feature.splash.model

sealed interface SplashEvent {
    data class SplashDone(val shouldOnboarding: Boolean) : SplashEvent
}
