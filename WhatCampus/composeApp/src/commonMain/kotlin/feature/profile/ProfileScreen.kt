package feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.theme.PaleGray
import core.di.koinViewModel
import feature.profile.components.ProfileTopBar
import feature.profile.components.SettingItem
import feature.profile.components.SettingSwitch
import feature.profile.components.UserInformation
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_push_allow_change
import whatcampus.composeapp.generated.resources.notice_push_category_change
import whatcampus.composeapp.generated.resources.university_department_change

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

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
                isChecked = uiState.user.isPushNotificationAllowed,
                onCheckedChange = viewModel::changePushNotificationAllowed,
            )

            SettingItem(
                title = stringResource(Res.string.notice_push_category_change),
                onClick = {},
            )

            SettingItem(
                title = stringResource(Res.string.university_department_change),
                onClick = {},
                withDivider = false,
            )
        }
    }
}
