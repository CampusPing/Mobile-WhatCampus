package feature.university.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.DuskGray
import core.designsystem.theme.LightGray
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White

@Composable
internal fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(size = 16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = hint
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = White,
            unfocusedBorderColor = LightGray,
            focusedBorderColor = Mint01,
            focusedLeadingIconColor = Mint01,
        ),
        textStyle = WhatcamTheme.typography.bodyLargeR,
        placeholder = {
            Text(
                text = hint,
                style = WhatcamTheme.typography.bodyLargeR,
                color = DuskGray,
            )
        },
    )
}
