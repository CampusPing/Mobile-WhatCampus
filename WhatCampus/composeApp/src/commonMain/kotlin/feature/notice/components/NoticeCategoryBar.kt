package feature.notice.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import core.model.NoticeCategory

@Composable
internal fun NoticeCategoryBar(
    modifier: Modifier = Modifier,
    noticeCategories: List<NoticeCategory>,
    selectedCategory: NoticeCategory? = noticeCategories.firstOrNull(),
    onClickCategory: (NoticeCategory) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .background(color = White)
            .fillMaxWidth()
            .horizontalScroll(state = scrollState)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        noticeCategories.forEach { category ->
            NoticeCategoryTab(
                modifier = Modifier.padding(horizontal = 4.dp),
                category = category,
                isSelected = selectedCategory == category,
                onClick = { onClickCategory(category) }
            )
        }
    }
}

@Composable
private fun NoticeCategoryTab(
    modifier: Modifier = Modifier,
    category: NoticeCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val cardColor by animateColorAsState(if (isSelected) WhatcamTheme.colors.primary else White)
    val textColor by animateColorAsState(if (isSelected) White else Graphite)

    Card(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .background(color = cardColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 8.dp),
    ) {
        Text(
            text = category.name,
            style = WhatcamTheme.typography.labelLargeR,
            color = textColor,
            modifier = Modifier.background(color = cardColor)
        )
    }
}

