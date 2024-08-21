package feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import core.common.extensions.navigateSingleTop
import core.designsystem.components.SimpleWebScreen
import core.navigation.Route
import feature.profile.ProfileScreen
import feature.profile.subscreen.noticeCategory.NoticeCategoryScreen

fun NavController.navigateProfile() {
    navigate(Route.Profile.route)
}

fun NavController.navigateNoticeCategory() {
    navigateSingleTop(
        route = ProfileRouteModel.NoticeCategory.route,
        isPopUpToTargetRoute = false,
    )
}

fun NavController.navigateFaq() {
    navigateSingleTop(
        ProfileRouteModel.Faq.route,
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.profileNavGraph(
    onClickBack: () -> Unit,
    onClickNoticeCategory: () -> Unit,
    onClickNoticeCategorySave: (savedMessage: String, actionLabel: String) -> Unit,
    onClickUniversityChange: () -> Unit,
    onClickFaq: () -> Unit,
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
                onClickFaq = onClickFaq,
            )
        }

        composable(ProfileRouteModel.NoticeCategory.route) {
            NoticeCategoryScreen(
                onClickBack = onClickBack,
                onClickSave = onClickNoticeCategorySave,
            )
        }

        composable(ProfileRouteModel.Faq.route) {
            SimpleWebScreen(url = FAQ_URL)
        }
    }
}

private const val FAQ_URL = "https://campusping.github.io/Introduce-WebPage-WhatCampus/faq"
