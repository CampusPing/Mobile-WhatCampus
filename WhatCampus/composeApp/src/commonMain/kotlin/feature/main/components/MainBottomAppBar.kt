package feature.main.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.LightGray
import core.designsystem.theme.WhatcamTheme
import feature.main.MainTab
import feature.main.navigation.MainNavigator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MainBottomAppBar(navigator: MainNavigator) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = WhatcamTheme.colors.primaryContainer,
        tonalElevation = 0.dp,
        actions = {
            MainTab.entries.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab.route == navigator.currentRoute,
                    onClick = {
                        navigator.navigate(tab.route)
                    },
                )
            }
        },
    )
}

@Composable
private fun RowScope.MainBottomBarItem(
    modifier: Modifier = Modifier,
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val color = if (selected) Graphite else LightGray

    Column(
        modifier = modifier
            .weight(1f)
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(tab.iconRes),
            contentDescription = stringResource(tab.titleRes),
            tint = color,
            modifier = Modifier.size(28.dp),
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = stringResource(tab.titleRes),
            style = WhatcamTheme.typography.labelSmallM,
            color = color,
        )
    }
}

