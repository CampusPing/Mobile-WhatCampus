package feature.main

import feature.main.navigation.MainRoute
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.bookmark_tab_title
import whatcampus.composeapp.generated.resources.campusmap_tab_title
import whatcampus.composeapp.generated.resources.chat_tab_title
import whatcampus.composeapp.generated.resources.ic_bookmark
import whatcampus.composeapp.generated.resources.ic_campus_map
import whatcampus.composeapp.generated.resources.ic_chat
import whatcampus.composeapp.generated.resources.ic_notice
import whatcampus.composeapp.generated.resources.notice_tab_title

enum class MainTab(
    val iconRes: DrawableResource,
    val titleRes: StringResource,
    val route: MainRoute,
) {
    NOTICE(
        iconRes = Res.drawable.ic_notice,
        titleRes = Res.string.notice_tab_title,
        route = MainRoute.NOTICE,
    ),
    BOOKMARK(
        iconRes = Res.drawable.ic_bookmark,
        titleRes = Res.string.bookmark_tab_title,
        route = MainRoute.BOOKMARK,
    ),
    CHAT(
        iconRes = Res.drawable.ic_chat,
        titleRes = Res.string.chat_tab_title,
        route = MainRoute.CHAT,
    ),
    CAMPUS_MAP(
        iconRes = Res.drawable.ic_campus_map,
        titleRes = Res.string.campusmap_tab_title,
        route = MainRoute.CAMPUS_MAP,
    );
}
