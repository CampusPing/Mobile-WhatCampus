package feature.university.navigation

import core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed class UniversityRoute : Route() {
    @Serializable
    data object UniversitySelectivityRoute : UniversityRoute()

    @Serializable
    data object DepartmentSelectivityRoute : UniversityRoute()

    @Serializable
    data object NoticeCategorySelectivityRoute : UniversityRoute()

    @Serializable
    data object UniversityCompleteRoute : UniversityRoute()
}
