package core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

open class CommonViewModel : ViewModel() {
    private val _commonUiEvent = MutableSharedFlow<CommonUiEvent>()
    val commonUiEvent = _commonUiEvent.asSharedFlow()

    suspend fun sendClientErrorEvent() {
        _commonUiEvent.emit(CommonUiEvent.ClientErrorOccurred)
    }

    suspend fun sendServerErrorEvent() {
        _commonUiEvent.emit(CommonUiEvent.ServerErrorOccurred)
    }

    suspend fun sendNetworkErrorEvent() {
        _commonUiEvent.emit(CommonUiEvent.NetworkErrorOccurred)
    }

    suspend fun sendOtherErrorEvent() {
        _commonUiEvent.emit(CommonUiEvent.OtherErrorOccurred)
    }
}
