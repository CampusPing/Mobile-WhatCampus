package feature.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.LightGray
import core.designsystem.theme.PaleGray
import core.designsystem.theme.WhatcamTheme

@Composable
internal fun SettingItem(
    title: String,
    onClick: () -> Unit,
    withDivider: Boolean = true,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = WhatcamTheme.typography.bodyLargeR,
            color = Graphite,
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = title,
            tint = Graphite,
            modifier = Modifier.padding(start = 8.dp),
        )
    }

    if (withDivider) {
        HorizontalDivider(
            thickness = 2.dp,
            color = PaleGray,
        )
    }
}

@Composable
internal fun SettingSwitch(
    title: String,
    withDivider: Boolean = true,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = WhatcamTheme.typography.bodyLargeR,
            color = Graphite,
        )

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = PaleGray,
                uncheckedThumbColor = LightGray,
                uncheckedBorderColor = LightGray,
            )
        )
    }

    if (withDivider) {
        HorizontalDivider(
            thickness = 2.dp,
            color = PaleGray,
        )
    }
}
