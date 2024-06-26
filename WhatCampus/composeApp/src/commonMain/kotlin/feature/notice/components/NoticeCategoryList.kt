package feature.notice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.model.NoticeCategory

@Composable
internal fun NoticeCategoryList(
    modifier: Modifier = Modifier,
    noticeCategories: List<NoticeCategory>,
    selectedCategory: NoticeCategory? = noticeCategories.firstOrNull(),
    onClickCategory: (NoticeCategory) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(state = scrollState)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        noticeCategories.forEach { category ->
            NoticeCategoryTab(
                category = category,
                isSelected = selectedCategory == category,
                onClick = { onClickCategory(category) }
            )
        }
    }
}

@Composable
private fun NoticeCategoryTab(
    category: NoticeCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
        onClick = onClick,
    ) {
        Text(
            text = category.name,
            style = WhatcamTheme.typography.labelLargeR,
            color = if (isSelected) WhatcamTheme.colors.primary else Graphite,
            modifier = Modifier.background(color = Color.Transparent)
        )
    }
}

