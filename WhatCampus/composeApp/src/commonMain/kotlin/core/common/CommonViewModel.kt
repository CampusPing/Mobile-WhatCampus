package core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

open class CommonViewModel : ViewModel() {
    protected val _commonUiEvent = MutableSharedFlow<CommonUiEvent>()
    val commonUiEvent = _commonUiEvent.asSharedFlow()
}
