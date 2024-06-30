package feature.noticeCategory.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.LightMint
import core.designsystem.theme.PaleGray
import core.designsystem.theme.PaperGray
import core.designsystem.theme.WhatcamTheme
import core.model.NoticeCategory
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.notice_category_desc

@Composable
internal fun NoticeCategoryList(
    modifier: Modifier = Modifier,
    noticeCategories: List<NoticeCategory>,
    selectedNoticeCategories: Set<NoticeCategory>,
    onClickCategory: (NoticeCategory) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 80.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        state = rememberLazyGridState()
    ) {
        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Text(
                text = stringResource(Res.string.notice_category_desc),
                style = WhatcamTheme.typography.titleLargeB,
                color = Graphite,
                modifier = Modifier.padding(bottom = 28.dp)
            )
        }

        items(
            items = noticeCategories,
            key = { noticeCategory -> noticeCategory.id }
        ) { noticeCategory ->
            NoticeCategoryItem(
                noticeCategory = noticeCategory,
                isSelected = noticeCategory in selectedNoticeCategories,
                onClick = { onClickCategory(noticeCategory) }
            )
        }
    }
}

@Composable
private fun NoticeCategoryItem(
    noticeCategory: NoticeCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val buttonColor by animateColorAsState(
        targetValue = if (isSelected) LightMint else PaleGray
    )
    val buttonStrokeColor by animateColorAsState(
        targetValue = if (isSelected) WhatcamTheme.colors.primary else PaperGray
    )

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = buttonColor),
        border = BorderStroke(width = 2.dp, color = buttonStrokeColor)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = noticeCategory.symbolEmoji,
                style = WhatcamTheme.typography.headlineLargeR,
                color = WhatcamTheme.colors.onPrimary,
            )

            Text(
                text = noticeCategory.name,
                style = WhatcamTheme.typography.labelMediumB,
                color = Graphite,
            )
        }
    }
}
