package feature.university.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.LightGray
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.model.Department

@Composable
internal fun DepartmentList(
    modifier: Modifier = Modifier,
    departments: List<Department>,
    selectedDepartment: Department?,
    onClickDepartment: (Department) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(
            items = departments,
            key = { department -> department.id }
        ) { department ->
            val borderWidth by animateDpAsState(
                targetValue = if (department == selectedDepartment) (2.5).dp else (1.5).dp
            )
            val borderColor by animateColorAsState(
                targetValue = if (department == selectedDepartment) Mint01 else LightGray
            )

            DepartmentItem(
                borderWidth = borderWidth,
                borderColor = borderColor,
                onClickDepartment = onClickDepartment,
                department = department,
            )
        }
    }
}

@Composable
private fun DepartmentItem(
    borderWidth: Dp,
    borderColor: Color,
    onClickDepartment: (Department) -> Unit,
    department: Department,
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
        onClick = { onClickDepartment(department) },
        shape = RoundedCornerShape(size = 16.dp),
    ) {
        Text(
            text = department.name,
            style = WhatcamTheme.typography.bodyLargeB,
            color = Graphite,
            textAlign = TextAlign.Center,
        )
    }
}
