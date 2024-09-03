package core.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import core.common.CommonUiEvent
import core.designsystem.theme.LocalSnackbarHostState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.default_client_error_message
import whatcampus.composeapp.generated.resources.default_network_error_message
import whatcampus.composeapp.generated.resources.default_other_error_message
import whatcampus.composeapp.generated.resources.default_server_error_message

@Composable
fun SharedFlow<CommonUiEvent>.collectUiEvent(
    onClientErrorOccurred: @Composable () -> Unit = { showDefaultClientErrorMessage() },
    onServerErrorOccurred: @Composable () -> Unit = { showDefaultServerErrorMessage() },
    onNetworkErrorOccurred: @Composable () -> Unit = { showDefaultNetworkErrorMessage() },
    onOtherErrorOccurred: @Composable () -> Unit = { showDefaultOtherErrorMessage() },
) {
    val currentOnClientErrorOccurred by rememberUpdatedState(onClientErrorOccurred)
    val currentOnServerErrorOccurred by rememberUpdatedState(onServerErrorOccurred)
    val currentOnNetworkErrorOccurred by rememberUpdatedState(onNetworkErrorOccurred)
    val currentOnOtherErrorOccurred by rememberUpdatedState(onOtherErrorOccurred)

    var currentUiEvent: CommonUiEvent? by remember { mutableStateOf(null) }

    LaunchedEffect(this) {
        collectLatest { uiEvent -> currentUiEvent = uiEvent }
    }

    when (currentUiEvent) {
        CommonUiEvent.ClientErrorOccurred -> currentOnClientErrorOccurred()
        CommonUiEvent.ServerErrorOccurred -> currentOnServerErrorOccurred()
        CommonUiEvent.NetworkErrorOccurred -> currentOnNetworkErrorOccurred()
        CommonUiEvent.OtherErrorOccurred -> currentOnOtherErrorOccurred()
    }
}

@Composable
private fun showDefaultClientErrorMessage() {
    val snackbarHostState = LocalSnackbarHostState.current
    val defaultClientErrorMessage = stringResource(Res.string.default_client_error_message)

    LaunchedEffect(snackbarHostState) {
        showSnackbar(snackbarHostState, defaultClientErrorMessage)
    }
}

@Composable
private fun showDefaultServerErrorMessage() {
    val snackbarHostState = LocalSnackbarHostState.current
    val serverErrorMessage = stringResource(Res.string.default_server_error_message)

    LaunchedEffect(snackbarHostState) {
        showSnackbar(snackbarHostState, serverErrorMessage)
    }
}

@Composable
private fun showDefaultNetworkErrorMessage() {
    val snackbarHostState = LocalSnackbarHostState.current
    val networkErrorMessage = stringResource(Res.string.default_network_error_message)

    LaunchedEffect(snackbarHostState) {
        showSnackbar(snackbarHostState, networkErrorMessage)
    }
}

@Composable
private fun showDefaultOtherErrorMessage() {
    val snackbarHostState = LocalSnackbarHostState.current
    val otherErrorMessage = stringResource(Res.string.default_other_error_message)

    LaunchedEffect(snackbarHostState) {
        showSnackbar(snackbarHostState, otherErrorMessage)
    }
}
