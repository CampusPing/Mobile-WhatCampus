package feature.app.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import feature.app.navigation.WhatcamNavigator
import feature.onboarding.navigation.onboardingNavGraph
import feature.university.navigation.universityNavGraph
import org.koin.compose.KoinContext

@Composable
internal fun WhatcamNavHost(
    navigator: WhatcamNavigator,
) {
    val initialScreensModifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp, bottom = 40.dp)

    KoinContext {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination.route,
        ) {
            onboardingNavGraph(
                modifier = initialScreensModifier,
                onboardingComplete = { navigator.navigateUniversitySelectivity() }
            )
            universityNavGraph(
                modifier = initialScreensModifier,
                onClickUniversity = { }
            )
        }
    }
}
