package feature.app.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import core.di.koinViewModel
import feature.app.navigation.WhatcamNavigator
import feature.main.navigation.mainNavGraph
import feature.onboarding.navigation.onboardingNavGraph
import feature.university.UniversityViewModel
import feature.university.navigation.universityNavGraph
import org.koin.compose.KoinContext

@Composable
internal fun WhatcamNavHost(
    navigator: WhatcamNavigator,
) {
    KoinContext {
        val universityViewModel = koinViewModel<UniversityViewModel>()

        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination.route,
        ) {
            onboardingNavGraph(
                onboardingComplete = { navigator.navigateUniversitySelectivity() }
            )
            universityNavGraph(
                viewModel = universityViewModel,
                onClickBack = { navigator.navigateUp() },
                onClickUniversity = { navigator.navigateDepartmentSelectivity() },
                onClickDepartment = { navigator.navigateUniversityComplete() },
                onClickComplete = { navigator.navigateMain() },
            )
            mainNavGraph()
        }
    }
}
