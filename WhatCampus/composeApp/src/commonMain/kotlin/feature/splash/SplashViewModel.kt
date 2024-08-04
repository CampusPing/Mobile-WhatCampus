package feature.splash

import androidx.lifecycle.ViewModel
import feature.splash.model.SplashEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SplashViewModel(
) : ViewModel() {
    private val _uiEvent = MutableSharedFlow<SplashEvent>()
    val uiEvent: SharedFlow<SplashEvent> = _uiEvent.asSharedFlow()

    init {

    }
}
