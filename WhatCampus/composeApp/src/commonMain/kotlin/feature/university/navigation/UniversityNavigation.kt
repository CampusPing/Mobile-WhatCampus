package feature.university.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.Route
import feature.university.DepartmentSelectivityScreen
import feature.university.NoticeCategorySelectivityScreen
import feature.university.UniversityCompleteScreen
import feature.university.UniversitySelectivityScreen
import feature.university.UniversityViewModel

fun NavController.navigateUniversitySelectivity() {
    navigate(Route.UniversitySelectivityRoute.route)
}

fun NavController.navigateDepartmentSelectivity() {
    navigate(Route.DepartmentSelectivityRoute.route)
}

fun NavController.navigateNoticeCategorySelectivity() {
    navigate(Route.NoticeCategorySelectivityRoute.route)
}

fun NavController.navigateUniversityComplete() {
    navigate(Route.UniversityCompleteRoute.route)
}

fun NavGraphBuilder.universityNavGraph(
    viewModel: UniversityViewModel,
    onClickBack: () -> Unit,
    onClickUniversity: () -> Unit,
    onClickDepartment: () -> Unit,
    onClickSaveNoticeCategory: () -> Unit,
    onClickComplete: () -> Unit,
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

    composable(Route.NoticeCategorySelectivityRoute.route) {
        NoticeCategorySelectivityScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 40.dp),
            viewModel = viewModel,
            onClickSave = onClickSaveNoticeCategory,
            onClickBack = onClickBack,
        )
    }

    composable(Route.UniversityCompleteRoute.route) {
        UniversityCompleteScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 40.dp),
            onClickComplete = onClickComplete,
            onClickBack = onClickBack,
        )
    }
}
