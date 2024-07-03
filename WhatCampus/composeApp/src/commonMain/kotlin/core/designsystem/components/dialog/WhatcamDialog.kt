package core.designsystem.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import core.designsystem.theme.Black
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.cancel
import whatcampus.composeapp.generated.resources.confirm


@Composable
fun WhatcamDialog(
    title: String,
    message: String,
    icon: Painter? = null,
    iconColor: Color = Color.Unspecified,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = onDismissClick,
    ) {
        WhatcamDialogContent(
            dialogIcon = icon,
            iconColor = iconColor,
            title = title,
            message = message,
            onClickOk = onConfirmClick,
            onClickNo = onDismissClick,
        )
    }
}

@Composable
private fun WhatcamDialogContent(
    modifier: Modifier = Modifier,
    dialogIcon: Painter?,
    iconColor: Color,
    title: String,
    message: String,
    onClickOk: () -> Unit,
    onClickNo: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(40.dp)
            .background(color = White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (dialogIcon != null) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = dialogIcon, tint = iconColor,
                contentDescription = null,
            )
        }

        DialogTitle(
            modifier = Modifier.padding(top = 4.dp),
            title = title,
        )

        if (message.isNotBlank()) {
            DialogMessage(
                modifier = Modifier.padding(top = 12.dp),
                message = message
            )
        }

        DialogTextButtons(
            modifier = Modifier.padding(top = 4.dp),
            negativeText = stringResource(Res.string.cancel),
            positiveText = stringResource(Res.string.confirm),
            onClickNo = onClickNo,
            onClickOk = onClickOk
        )
    }
}

@Composable
private fun DialogTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier.padding(top = 4.dp),
        text = title,
        color = Graphite,
        textAlign = TextAlign.Center,
        style = WhatcamTheme.typography.titleLargeB,
    )
}

@Composable
private fun DialogMessage(
    modifier: Modifier = Modifier,
    message: String,
) {
    Text(
        modifier = modifier.padding(bottom = 8.dp),
        text = message,
        color = Black,
        textAlign = TextAlign.Center,
        style = WhatcamTheme.typography.bodyLargeR,
    )
}

@Composable
private fun DialogTextButtons(
    modifier: Modifier = Modifier,
    negativeText: String,
    positiveText: String,
    onClickNo: () -> Unit,
    onClickOk: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        if (negativeText.isNotBlank()) {
            DialogTextButton(
                modifier = Modifier.weight(1f),
                text = negativeText,
                fontColor = Graphite,
                onClick = onClickNo,
            )
        }
        DialogTextButton(
            modifier = Modifier.weight(1f),
            text = positiveText,
            fontColor = WhatcamTheme.colors.primary,
            onClick = onClickOk,
        )
    }
}

@Composable
private fun DialogTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    fontColor: Color,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = fontColor,
            textAlign = TextAlign.Center,
            style = WhatcamTheme.typography.labelLargeB,
        )
    }
}
