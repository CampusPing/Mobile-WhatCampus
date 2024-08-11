package feature.noticeSearch.components

import androidx.compose.runtime.Composable
import core.designsystem.components.dialog.MutableDialogState
import core.designsystem.components.dialog.WhatcamDialog
import feature.noticeSearch.model.NoticeSearchClearType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.ic_alert
import whatcampus.composeapp.generated.resources.notice_search_delete_all_dialog_message
import whatcampus.composeapp.generated.resources.notice_search_delete_dialog_message
import whatcampus.composeapp.generated.resources.notice_search_delete_dialog_title

@Composable
internal fun NoticeSearchDeleteDialog(
    dialogState: MutableDialogState<NoticeSearchClearType>,
    onClickDeleteHistory: (String) -> Unit,
    onClickDeleteAllHistory: () -> Unit,
) {
    when (val noticeSearchClearType = dialogState.dialogData.value) {
        is NoticeSearchClearType.Delete -> {
            WhatcamDialog(
                title = stringResource(Res.string.notice_search_delete_dialog_title),
                message = stringResource(
                    Res.string.notice_search_delete_dialog_message,
                    noticeSearchClearType.query
                ),
                onConfirmClick = { onClickDeleteHistory(noticeSearchClearType.query) },
                onDismissClick = dialogState::hideDialog,
            )
        }

        NoticeSearchClearType.DeleteAll -> {
            WhatcamDialog(
                title = stringResource(Res.string.notice_search_delete_dialog_title),
                message = stringResource(Res.string.notice_search_delete_all_dialog_message),
                icon = painterResource(Res.drawable.ic_alert),
                onConfirmClick = onClickDeleteAllHistory,
                onDismissClick = dialogState::hideDialog,
            )
        }

        NoticeSearchClearType.Nothing -> Unit
    }
}
