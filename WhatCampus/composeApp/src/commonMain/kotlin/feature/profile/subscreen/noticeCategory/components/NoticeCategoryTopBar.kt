package feature.profile.subscreen.noticeCategory.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_category_save
import whatcampus.composeapp.generated.resources.notice_category_title
import whatcampus.composeapp.generated.resources.profile_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoticeCategoryTopBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickSave: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.notice_category_title),
                style = WhatcamTheme.typography.titleMediumB,
                color = Graphite,
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(Res.string.profile_title),
                    tint = Graphite,
                )
            }
        },
        actions = {
            IconButton(onClick = onClickSave) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(Res.string.notice_category_save),
                    tint = WhatcamTheme.colors.primary,
                )
            }
        }
    )
}
