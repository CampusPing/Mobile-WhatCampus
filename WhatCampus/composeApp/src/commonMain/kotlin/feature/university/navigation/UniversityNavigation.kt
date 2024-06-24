package feature.university.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.common.extensions.navigateSingleTop
import core.navigation.Route
import feature.university.DepartmentSelectivityScreen
import feature.university.UniversitySelectivityScreen
import feature.university.UniversityViewModel

fun NavController.navigateUniversitySelectivity() {
    navigate(Route.UniversitySelectivityRoute.route)
}

fun NavController.navigateDepartmentSelectivity() {
    navigate(Route.DepartmentSelectivityRoute.route)
}

fun NavGraphBuilder.universityNavGraph(
    viewModel: UniversityViewModel,
    onClickBack: () -> Unit,
    onClickUniversity: () -> Unit,
    onClickDepartment: () -> Unit,
) {
    composable(Route.UniversitySelectivityRoute.route) {
        UniversitySelectivityScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            viewModel = viewModel,
            onClickUniversity = onClickUniversity,
        )
    }

    composable(Route.DepartmentSelectivityRoute.route) {
        DepartmentSelectivityScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            viewModel = viewModel,
            onClickDepartment = onClickDepartment,
            onClickBack = onClickBack,
        )
    }
}
