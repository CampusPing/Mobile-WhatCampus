package feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.repository.UserRepository
import feature.splash.model.SplashEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class SplashViewModel(
    userRepository: UserRepository,
) : ViewModel() {
    private val _uiEvent = MutableSharedFlow<SplashEvent>()
    val uiEvent: SharedFlow<SplashEvent> = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            val startTime: Instant = Clock.System.now()
            userRepository.flowUser()
                .onEach { user ->
                    val workedTime = Clock.System.now() - startTime
                    val remainingTime = MIN_SPLASH_DURATION - workedTime

                    if (remainingTime.isPositive()) {
                        delay(remainingTime.inWholeMilliseconds)
                    }
                    _uiEvent.emit(SplashEvent.SplashDone(shouldOnboarding = (user == null)))
                }
                .launchIn(viewModelScope)
        }
    }

    companion object {
        private const val MIN_SPLASH_DURATION_MS: Long = 1500L
        private val MIN_SPLASH_DURATION: Duration = MIN_SPLASH_DURATION_MS.toDuration(DurationUnit.MILLISECONDS)
    }
}
