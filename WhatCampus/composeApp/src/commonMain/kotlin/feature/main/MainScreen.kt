package feature.main

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import feature.main.navigation.MainRoute
import feature.main.navigation.rememberMainNavigator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.app_name
import whatcampus.composeapp.generated.resources.ic_bookmark
import whatcampus.composeapp.generated.resources.ic_profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navigator = rememberMainNavigator()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_bookmark),
                        contentDescription = stringResource(Res.string.app_name),
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = null,
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(Res.drawable.ic_profile),
                            contentDescription = null,
                        )
                    }
                }
            )
        },
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination.route,
        ) {
            composable(MainRoute.NOTICE.route) {
//                NoticeScreen()
            }
            composable(MainRoute.BOOKMARK.route) {
//                BookmarkScreen()
            }
            composable(MainRoute.CHAT.route) {
//                ChatScreen()
            }
            composable(MainRoute.CAMPUS_MAP.route) {
//                CampusMapScreen()
            }
        }
    }
}
