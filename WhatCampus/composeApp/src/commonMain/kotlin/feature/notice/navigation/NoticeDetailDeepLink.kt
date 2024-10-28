package feature.notice.navigation

import core.model.Notice
import core.navigation.MainRoute
import feature.app.navigation.DeepLink
import feature.app.navigation.WhatcamNavigator

class NoticeDetailDeepLink(
    private val notice: Notice,
) : DeepLink() {
    override fun handleDeepLink(whatcamNavigator: WhatcamNavigator) {
        if (whatcamNavigator.currentRoute == MainRoute.SplashRoute) {
            whatcamNavigator.navController.popBackStack(MainRoute.SplashRoute, true)
            whatcamNavigator.navigateMain()
        }

        whatcamNavigator.navigateNoticeDetail(notice)
    }
}
