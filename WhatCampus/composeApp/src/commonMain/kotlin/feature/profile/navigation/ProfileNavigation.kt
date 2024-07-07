package feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.profile.ProfileScreen
import feature.profile.subscreen.noticeCategory.NoticeCategoryScreen

fun NavController.navigateProfile() {
    navigate(Route.Profile.route)
}

fun NavController.navigateNoticeCategory() {
    navigateSingleTop(
        route = ProfileRouteModel.NoticeCategory.route,
        isPopUpToStartDestination = false,
    )
}

fun NavGraphBuilder.profileNavGraph(
    onClickBack: () -> Unit,
    onClickNoticeCategory: () -> Unit,
    onClickSave: (savedMessage: String, actionLabel: String) -> Unit,
    onClickUniversityChange: () -> Unit,
) {
    navigation(
        startDestination = ProfileRouteModel.ProfileMain.route,
        route = Route.Profile.route,
    ) {
        composable(ProfileRouteModel.ProfileMain.route) {
            ProfileScreen(
                onClickBack = onClickBack,
                onClickNoticeCategory = onClickNoticeCategory,
                onClickUniversityChange = onClickUniversityChange,
            )
        }

        composable(ProfileRouteModel.NoticeCategory.route) {
            NoticeCategoryScreen(
                onClickBack = onClickBack,
                onClickSave = onClickSave,
            )
        }
    }
}
