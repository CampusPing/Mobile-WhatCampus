package feature.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import core.model.Notice
import core.navigation.Route
import feature.main.navigation.navigateMain
import feature.notice.navigation.navigateNoticeDetail
import feature.noticeSearch.navigation.navigateNoticeSearch
import feature.onboarding.navigation.navigateOnboarding
import feature.profile.navigation.navigateNoticeCategory
import feature.profile.navigation.navigateProfile
import feature.university.navigation.navigateDepartmentSelectivity
import feature.university.navigation.navigateNoticeCategorySelectivity
import feature.university.navigation.navigateUniversityComplete
import feature.university.navigation.navigateUniversitySelectivity

internal class WhatcamNavigator(
    val navController: NavHostController,
) {
    val startDestination: Route = Route.SplashRoute

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateOnboarding(popUpTargetRoute: Route? = null) {
        navController.navigateOnboarding(popUpTargetRoute = popUpTargetRoute)
    }

    fun navigateUniversitySelectivity() {
        navController.navigateUniversitySelectivity()
    }

    fun navigateDepartmentSelectivity() {
        navController.navigateDepartmentSelectivity()
    }

    fun navigateNoticeCategorySelectivity() {
        navController.navigateNoticeCategorySelectivity()
    }

    fun navigateUniversityComplete() {
        navController.navigateUniversityComplete()
    }

    fun navigateMain(popUpTargetRoute: Route? = null) {
        navController.navigateMain(popUpTargetRoute = popUpTargetRoute)
    }

    fun navigateNoticeDetail(notice: Notice) {
        navController.navigateNoticeDetail(notice = notice)
    }

    fun navigateNoticeSearch() {
        navController.navigateNoticeSearch()
    }

    fun navigateNoticeCategory() {
        navController.navigateNoticeCategory()
    }

    fun navigateProfile() {
        navController.navigateProfile()
    }
}

@Composable
internal fun rememberWhatcamNavigator(
    navController: NavHostController = rememberNavController(),
): WhatcamNavigator = remember(navController) {
    WhatcamNavigator(navController = navController)
}
