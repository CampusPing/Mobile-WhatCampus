package core.navigation

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
    Profile("profile"),
}
