package feature.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import core.model.Notice
import core.navigation.MainRoute
import feature.main.navigation.navigateMain
import feature.notice.navigation.navigateNoticeDetail
import feature.noticeSearch.navigation.navigateNoticeSearch
import feature.notificationArchive.navigation.navigateNotificationArchive
import feature.onboarding.navigation.navigateOnboarding
import feature.profile.navigation.navigateFaq
import feature.profile.navigation.navigateNoticeCategory
import feature.profile.navigation.navigatePrivacy
import feature.profile.navigation.navigateProfile
import feature.university.navigation.navigateDepartmentSelectivity
import feature.university.navigation.navigateNoticeCategorySelectivity
import feature.university.navigation.navigateUniversityComplete
import feature.university.navigation.navigateUniversitySelectivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WhatcamNavigator(
    val navController: NavHostController,
) {
    val startDestination: MainRoute = MainRoute.SplashRoute
    val currentRoute: MainRoute
        get() = navController.currentBackStackEntry?.toRoute() ?: startDestination

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateOnboarding(popUpTargetRoute: MainRoute? = null) {
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

    fun navigateMain(popUpTargetRoute: MainRoute? = null) {
        navController.navigateMain(popUpTargetRoute = popUpTargetRoute)
    }

    fun navigateNoticeDetail(notice: Notice) {
        navController.navigateNoticeDetail(notice = notice)
    }

    fun navigateNotificationArchive() {
        navController.navigateNotificationArchive()
    }

    fun navigateNoticeSearch() {
        navController.navigateNoticeSearch()
    }

    fun navigateProfile() {
        navController.navigateProfile()
    }

    fun navigateNoticeCategory() {
        navController.navigateNoticeCategory()
    }

    fun navigateFaq() {
        navController.navigateFaq()
    }

    fun navigatePrivacy() {
        navController.navigatePrivacy()
    }

    companion object {
        private var navigator: WhatcamNavigator? = null

        fun init(whatcamNavigator: WhatcamNavigator) {
            navigator = whatcamNavigator
        }

        fun handleDeeplink(deepLink: DeepLink) {
            MainScope().launch {
                while (navigator == null) {
                    delay(300)
                }
                deepLink.handleDeepLink(whatcamNavigator = navigator ?: return@launch)
            }
        }
    }
}

@Composable
internal fun rememberWhatcamNavigator(
    navController: NavHostController = rememberNavController(),
): WhatcamNavigator = remember(navController) {
    WhatcamNavigator(navController = navController)
}
