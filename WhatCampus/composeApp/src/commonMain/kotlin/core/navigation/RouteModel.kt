package core.navigation

import androidx.navigation.NavDestination

enum class Route(
    val route: String,
) {
    SplashRoute("splash"),
    OnboardingRoute("onboarding"),
    UniversitySelectivityRoute("universitySelectivity"),
    DepartmentSelectivityRoute("departmentSelectivity"),
    NoticeCategorySelectivityRoute("noticeCategorySelectivity"),
    UniversityCompleteRoute("universityComplete"),
    MainRoute("main"),
    NoticeDetail("noticeDetail"),
    NoticeSearch("noticeSearch"),
    Profile("profile");

    companion object {
        fun fromDestination(destination: NavDestination?): Route {
            return entries.find { it.route == destination?.route } ?: SplashRoute
        }
    }
}
