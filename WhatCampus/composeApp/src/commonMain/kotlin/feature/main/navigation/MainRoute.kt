package feature.main.navigation

enum class MainRoute(
    val route: String,
) {
    NOTICE("notice"),
    BOOKMARK("bookmark"),
    CHAT("chat"),
    CAMPUS_MAP("campusMap");

    companion object {
        fun of(route: String): MainRoute = entries.first { mainRoute ->
            mainRoute.route == route
        }
    }
}
