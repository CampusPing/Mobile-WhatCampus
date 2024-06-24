package feature.university.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designsystem.theme.Graphite
import core.designsystem.theme.LightGray
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.domain.University
import feature.university.toEmoji

@Composable
internal fun UniversityList(
    modifier: Modifier = Modifier,
    universities: List<University> = emptyList(),
    selectedUniversity: University? = null,
    onClickUniversity: (University) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(universities) { university ->
            val borderWidth by animateDpAsState(
                targetValue = if (university == selectedUniversity) (2.5).dp else (1.5).dp
            )
            val borderColor by animateColorAsState(
                targetValue = if (university == selectedUniversity) Mint01 else LightGray
            )

            UniversityItem(
                borderWidth = borderWidth,
                borderColor = borderColor,
                onClickUniversity = onClickUniversity,
                university = university,
            )
        }
    }
}

@Composable
private fun UniversityItem(
    borderWidth: Dp,
    borderColor: Color,
    onClickUniversity: (University) -> Unit,
    university: University,
) {
    OutlinedButton(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .heightIn(min = 60.dp),
        border = BorderStroke(
            width = borderWidth,
            color = borderColor,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = White,
        ),
        onClick = { onClickUniversity(university) },
        shape = RoundedCornerShape(size = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = university.toEmoji(),
                fontSize = 26.sp,
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = university.name,
                style = WhatcamTheme.typography.bodyLargeB,
                color = Graphite,
            )
        }
    }
}
