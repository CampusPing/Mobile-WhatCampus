package core.navigation

enum class Route(
    val route: String,
) {
    OnboardingRoute("onboarding"),
    UniversitySelectivityRoute("universitySelectivity"),
    DepartmentSelectivityRoute("departmentSelectivity"),
    UniversityCompleteRoute("universityComplete"),
    MainRoute("main"),
    NoticeDetail("noticeDetail"),
    NoticeSearch("noticeSearch"),
    NoticeCategory("noticeCategory"),
}
