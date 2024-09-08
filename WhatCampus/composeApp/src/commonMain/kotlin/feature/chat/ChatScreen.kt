package feature.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.common.util.logScreenEvent
import feature.chat.components.ChatPreparation
import feature.chat.components.ChatTopAppBar

@Composable
internal fun ChatScreen(
    modifier: Modifier = Modifier,
) {
    logScreenEvent(screenName = "ChatScreen")

    Scaffold(
        modifier = modifier,
        topBar = { ChatTopAppBar() },
    ) { paddingValues ->
        ChatPreparation(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

