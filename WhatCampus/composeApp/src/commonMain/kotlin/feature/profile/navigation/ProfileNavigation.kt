package feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import core.common.extensions.navigateSingleTop
import core.designsystem.components.SimpleWebScreen
import core.navigation.MainRoute
import feature.profile.ProfileScreen
import feature.profile.subscreen.noticeCategory.NoticeCategoryScreen

fun NavController.navigateProfile() {
    navigate(MainRoute.Profile)
}

fun NavController.navigateNoticeCategory() {
    navigateSingleTop(
        route = ProfileRouteModel.NoticeCategory,
        isPopUpToTargetRoute = false,
    )
}

fun NavController.navigateFaq() {
    navigateSingleTop(
        ProfileRouteModel.Faq,
        isPopUpToTargetRoute = false,
    )
}

fun NavController.navigatePrivacy() {
    navigateSingleTop(
        ProfileRouteModel.Privacy,
        isPopUpToTargetRoute = false,
    )
}

fun NavGraphBuilder.profileNavGraph(
    onClickBack: () -> Unit,
    onClickNoticeCategory: () -> Unit,
    onClickNoticeCategorySave: (savedMessage: String, actionLabel: String) -> Unit,
    onClickUniversityChange: () -> Unit,
    onClickFaq: () -> Unit,
    onClickPrivacy: () -> Unit,
) {
    navigation<MainRoute.Profile>(
        startDestination = ProfileRouteModel.ProfileMain
    ) {
        composable<ProfileRouteModel.ProfileMain> {
            ProfileScreen(
                onClickBack = onClickBack,
                onClickNoticeCategory = onClickNoticeCategory,
                onClickUniversityChange = onClickUniversityChange,
                onClickFaq = onClickFaq,
                onClickPrivacy = onClickPrivacy,
            )
        }

        composable<ProfileRouteModel.NoticeCategory> {
            NoticeCategoryScreen(
                onClickBack = onClickBack,
                onClickSave = onClickNoticeCategorySave,
            )
        }

        composable<ProfileRouteModel.Faq> {
            SimpleWebScreen(url = FAQ_URL)
        }

        composable<ProfileRouteModel.Privacy> {
            SimpleWebScreen(url = PRIVACY_URL)
        }
    }
}

private const val FAQ_URL = "https://campusping.github.io/Introduce-WebPage-WhatCampus/faq"
private const val PRIVACY_URL = "https://campusping.github.io/Introduce-WebPage-WhatCampus/privacy"
