package feature.notice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.app_name
import whatcampus.composeapp.generated.resources.ic_profile
import whatcampus.composeapp.generated.resources.img_logo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoticeTopAppBar(
    modifier: Modifier = Modifier,
    onClickSearch: () -> Unit,
    onClickNotificationArchive: () -> Unit,
    onClickProfile: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.img_logo),
                    contentDescription = stringResource(Res.string.app_name),
                    tint = WhatcamTheme.colors.primary,
                    modifier = Modifier.size(40.dp),
                )

                Spacer(modifier = Modifier.padding(start = 4.dp))

                Text(
                    text = stringResource(Res.string.app_name),
                    style = WhatcamTheme.typography.titleLargeB,
                    color = Graphite,
                    modifier = Modifier.absoluteOffset { IntOffset(x = 0, y = -8) },
                )
            }
        },
        actions = {
            MainIconButton(
                imageVector = Icons.Filled.Search,
                onClick = onClickSearch,
            )

            MainIconButton(
                imageVector = Icons.Outlined.Notifications,
                onClick = onClickNotificationArchive,
            )

            ProfileButton(
                onClick = onClickProfile
            )

            Spacer(modifier = Modifier.padding(end = 8.dp))
        },
    )
}

@Composable
private fun MainIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Graphite,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = tint,
        )
    }
}

@Composable
private fun ProfileButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(Res.drawable.ic_profile),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(28.dp),
        )
    }
}
