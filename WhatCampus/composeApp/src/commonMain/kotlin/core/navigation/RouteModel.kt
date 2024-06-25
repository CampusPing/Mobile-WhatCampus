package core.navigation

enum class Route(
    val route: String,
) {
    OnboardingRoute("onboarding"),
    UniversitySelectivityRoute("universitySelectivity"),
    DepartmentSelectivityRoute("departmentSelectivity"),
    UniversityCompleteRoute("universityComplete"),
}

enum class MainRoute(
    val route: String,
) {
    NOTICE("notice"),
    BOOKMARK("bookmark"),
    CHAT("chat"),
    CAMPUS_MAP("campusMap"),
}
