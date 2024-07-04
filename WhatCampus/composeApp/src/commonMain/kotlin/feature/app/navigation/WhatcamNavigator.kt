package feature.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import core.model.Notice
import core.navigation.Route
import feature.main.navigation.navigateMain
import feature.notice.navigation.navigateNoticeDetail
import feature.noticeCategory.navigation.navigateNoticeCategory
import feature.noticeSearch.navigation.navigateNoticeSearch
import feature.university.navigation.navigateDepartmentSelectivity
import feature.university.navigation.navigateNoticeCategorySelectivity
import feature.university.navigation.navigateUniversityComplete
import feature.university.navigation.navigateUniversitySelectivity

internal class WhatcamNavigator(
    val navController: NavHostController,
) {
    val startDestination: Route = Route.OnboardingRoute
//    val startDestination: Route = Route.MainRoute

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateUp() {
        navController.navigateUp()
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

    fun navigateMain() {
        navController.navigateMain()
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
}

@Composable
internal fun rememberWhatcamNavigator(
    navController: NavHostController = rememberNavController(),
): WhatcamNavigator = remember(navController) {
    WhatcamNavigator(navController = navController)
}
