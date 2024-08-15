package feature.notice.navigation

import core.model.Notice
import core.navigation.Route
import feature.app.navigation.DeepLink
import feature.app.navigation.WhatcamNavigator

class NoticeDetailDeepLink(
    private val notice: Notice,
) : DeepLink() {
    override fun handleDeepLink(whatcamNavigator: WhatcamNavigator) {
        if (whatcamNavigator.currentRoute == Route.SplashRoute) {
            whatcamNavigator.navController.popBackStack(Route.SplashRoute.route, true)
            whatcamNavigator.navigateMain()
        }

        whatcamNavigator.navigateNoticeDetail(notice)
    }
}
