package core.common.extensions

import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException

suspend fun PermissionsController.provideOrRequestNotificationPermission(
    onStateChanged: (PermissionState) -> Unit = {},
) {
    try {
        providePermission(Permission.REMOTE_NOTIFICATION)
        onStateChanged(PermissionState.Granted)
    } catch (e: DeniedAlwaysException) {
        onStateChanged(PermissionState.DeniedAlways)
    } catch (e: DeniedException) {
        onStateChanged(PermissionState.Denied)
    } catch (e: RequestCanceledException) {
        e.printStackTrace()
    }
}
