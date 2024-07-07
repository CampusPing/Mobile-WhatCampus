package feature.app.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import core.di.koinViewModel
import feature.app.navigation.WhatcamNavigator
import feature.main.navigation.mainNavGraph
import feature.notice.navigation.noticeDetailNavGraph
import feature.noticeSearch.navigation.noticeSearchNavGraph
import feature.onboarding.navigation.onboardingNavGraph
import feature.profile.navigation.profileNavGraph
import feature.university.UniversityViewModel
import feature.university.navigation.universityNavGraph
import org.koin.compose.KoinContext

@Composable
internal fun WhatcamNavHost(
    navigator: WhatcamNavigator,
    onShowSnackbar: (message: String, actionLabel: String?) -> Unit,
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
                onClickBack = navigator::navigateUp,
                onClickUniversity = { navigator.navigateDepartmentSelectivity() },
                onClickDepartment = { navigator.navigateNoticeCategorySelectivity() },
                onClickSaveNoticeCategory = { navigator.navigateUniversityComplete() },
                onClickComplete = { navigator.navigateMain() },
            )
            mainNavGraph(
                onNoticeClick = navigator::navigateNoticeDetail,
                onClickNoticeSearch = navigator::navigateNoticeSearch,
                onClickProfile = navigator::navigateProfile,
            )
            noticeDetailNavGraph(
                onClickBack = { navigator.navigateUp() },
            )
            noticeSearchNavGraph(
                onClickBack = { navigator.navigateUp() },
                onClickNotice = navigator::navigateNoticeDetail
            )
            profileNavGraph(
                onClickBack = navigator::navigateUp,
                onClickNoticeCategory = navigator::navigateNoticeCategory,
                onClickSave = { savedMessage, actionLabel ->
                    navigator.navigateUp()
                    onShowSnackbar(savedMessage, actionLabel)
                },
                onClickUniversityChange = navigator::navigateOnboarding,
            )
        }
    }
}
