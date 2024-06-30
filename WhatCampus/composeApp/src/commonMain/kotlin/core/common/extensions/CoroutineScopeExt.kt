package core.common.extensions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.showSnackbar(
    snackBarState: SnackbarHostState,
    message: String,
    actionLabel: String? = null,
    onActionPerformed: () -> Unit = { snackBarState.currentSnackbarData?.dismiss() },
) {
    snackBarState.currentSnackbarData?.dismiss()

    launch {
        val snackbarResult = snackBarState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )

        when (snackbarResult) {
            SnackbarResult.ActionPerformed -> onActionPerformed()
            else -> Unit
        }
    }
}
