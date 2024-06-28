package feature.bookmark.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.bookmark_cancel
import whatcampus.composeapp.generated.resources.bookmark_edit
import whatcampus.composeapp.generated.resources.bookmark_title
import whatcampus.composeapp.generated.resources.unbookmark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookmarkTopBar(
    modifier: Modifier = Modifier,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
    onClickSelectAll: () -> Unit,
    onClickUnbookmark: () -> Unit,
    isEditMode: Boolean,
    isAllSelected: Boolean,
    isShowActions: Boolean,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.bookmark_title),
                style = WhatcamTheme.typography.titleMediumB,
                color = Graphite,
            )
        },
        navigationIcon = {
            if (isEditMode) {
                IconButton(onClick = onClickCancel) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(Res.string.bookmark_cancel),
                        tint = Graphite,
                    )
                }
            }
        },
        actions = {
            if (isShowActions) {
                if (isEditMode) {
                    RadioButton(
                        selected = isAllSelected,
                        onClick = onClickSelectAll,
                    )

                    IconButton(onClick = onClickUnbookmark) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(Res.string.unbookmark),
                            tint = Graphite,
                        )
                    }
                } else {
                    IconButton(onClick = onClickEdit) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(Res.string.bookmark_edit),
                            tint = Graphite,
                        )
                    }
                }
            }
        },
    )
}
