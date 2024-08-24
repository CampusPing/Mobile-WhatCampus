package feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.common.extensions.provideOrRequestNotificationPermission
import core.designsystem.components.dialog.WhatcamDialog
import core.designsystem.components.dialog.rememberDialogState
import core.designsystem.theme.PaleGray
import core.di.koinViewModel
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import feature.profile.components.ProfileTopBar
import feature.profile.components.SettingItem
import feature.profile.components.SettingSwitch
import feature.profile.components.UserInformation
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.faq
import whatcampus.composeapp.generated.resources.ic_alert
import whatcampus.composeapp.generated.resources.notice_push_allow_change
import whatcampus.composeapp.generated.resources.notice_push_category_change
import whatcampus.composeapp.generated.resources.privacy
import whatcampus.composeapp.generated.resources.university_department_change
import whatcampus.composeapp.generated.resources.university_department_change_dialog_message
import whatcampus.composeapp.generated.resources.university_department_change_dialog_title

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onClickBack: () -> Unit,
    onClickNoticeCategory: () -> Unit,
    onClickUniversityChange: () -> Unit,
    onClickFaq: () -> Unit,
    onClickPrivacy: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    val dialogState = rememberDialogState()
    var notificationPermissionState by remember { mutableStateOf(PermissionState.NotDetermined) }
    val isNotificationPermissionGranted by remember {
        derivedStateOf { notificationPermissionState == PermissionState.Granted }
    }

    val permissionControllerFactory = rememberPermissionsControllerFactory()
    val permissionController = remember(permissionControllerFactory) {
        permissionControllerFactory.createPermissionsController()
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        notificationPermissionState = permissionController.getPermissionState(Permission.REMOTE_NOTIFICATION)
    }

    BindEffect(permissionController)

    Scaffold(
        modifier = modifier,
        topBar = {
            ProfileTopBar(onClickBack = onClickBack)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            UserInformation(
                universityName = uiState.user.universityName,
                departmentName = uiState.user.departmentName,
            )

            HorizontalDivider(
                thickness = 12.dp,
                color = PaleGray,
            )

            SettingSwitch(
                title = stringResource(Res.string.notice_push_allow_change),
                isChecked = uiState.user.isPushNotificationAllowed && isNotificationPermissionGranted,
                onCheckedChange = { isAllowed ->
                    coroutineScope.launch {
                        permissionController.provideOrRequestNotificationPermission { newState ->
                            notificationPermissionState = newState
                            when (newState) {
                                PermissionState.Granted -> viewModel.changePushNotificationAllowed(
                                    isAllowed
                                )

                                PermissionState.Denied -> viewModel.changePushNotificationAllowed(
                                    false
                                )

                                else -> permissionController.openAppSettings()
                            }
                        }
                    }
                },
            )

            SettingItem(
                title = stringResource(Res.string.notice_push_category_change),
                onClick = onClickNoticeCategory,
            )

            SettingItem(
                title = stringResource(Res.string.university_department_change),
                onClick = dialogState::showDialog,
            )

            SettingItem(
                title = stringResource(Res.string.faq),
                onClick = onClickFaq,
            )

            SettingItem(
                title = stringResource(Res.string.privacy),
                onClick = onClickPrivacy,
                withDivider = false,
            )
        }

        if (dialogState.isVisible.value) {
            WhatcamDialog(
                title = stringResource(Res.string.university_department_change_dialog_title),
                message = stringResource(Res.string.university_department_change_dialog_message),
                icon = painterResource(Res.drawable.ic_alert),
                onConfirmClick = {
                    dialogState.hideDialog()
                    onClickUniversityChange()
                },
                onDismissClick = dialogState::hideDialog,
            )
        }
    }
}
