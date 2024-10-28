package feature.university.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import core.navigation.MainRoute
import feature.university.DepartmentSelectivityScreen
import feature.university.NoticeCategorySelectivityScreen
import feature.university.UniversityCompleteScreen
import feature.university.UniversitySelectivityScreen
import feature.university.UniversityViewModel

fun NavController.navigateUniversitySelectivity() {
    navigate(UniversityRoute.UniversitySelectivityRoute)
}

fun NavController.navigateDepartmentSelectivity() {
    navigate(UniversityRoute.DepartmentSelectivityRoute)
}

fun NavController.navigateNoticeCategorySelectivity() {
    navigate(UniversityRoute.NoticeCategorySelectivityRoute)
}

fun NavController.navigateUniversityComplete() {
    navigate(UniversityRoute.UniversityCompleteRoute)
}

fun NavGraphBuilder.universityNavGraph(
    viewModel: UniversityViewModel,
    onClickBack: () -> Unit,
    onClickUniversity: () -> Unit,
    onClickDepartment: () -> Unit,
    onClickSaveNoticeCategory: () -> Unit,
    onClickComplete: () -> Unit,
) {
    navigation<MainRoute.UniversityRoute>(
        startDestination = UniversityRoute.UniversitySelectivityRoute,
    ) {
        composable<UniversityRoute.UniversitySelectivityRoute> {
            UniversitySelectivityScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                viewModel = viewModel,
                onClickUniversity = onClickUniversity,
            )
        }

        composable<UniversityRoute.DepartmentSelectivityRoute> {
            DepartmentSelectivityScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                viewModel = viewModel,
                onClickDepartment = onClickDepartment,
                onClickBack = onClickBack,
            )
        }

        composable<UniversityRoute.NoticeCategorySelectivityRoute> {
            NoticeCategorySelectivityScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 40.dp),
                viewModel = viewModel,
                onClickSave = onClickSaveNoticeCategory,
                onClickBack = onClickBack,
            )
        }

        composable<UniversityRoute.UniversityCompleteRoute> {
            UniversityCompleteScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 40.dp),
                onClickComplete = onClickComplete,
                onClickBack = onClickBack,
            )
        }
    }
}
