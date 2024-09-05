package core.common

abstract class CommonUiEvent {
    data object ClientErrorOccurred : CommonUiEvent()
    data object ServerErrorOccurred : CommonUiEvent()
    data object NetworkErrorOccurred : CommonUiEvent()
    data object OtherErrorOccurred : CommonUiEvent()
}
