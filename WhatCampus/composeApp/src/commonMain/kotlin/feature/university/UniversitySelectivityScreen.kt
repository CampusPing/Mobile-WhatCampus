package feature.university

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.theme.Graphite
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import core.di.koinViewModel
import core.model.University
import feature.university.components.SearchBar
import feature.university.components.UniversityList
import feature.university.model.UniversityUiState
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.university_desc
import whatcampus.composeapp.generated.resources.university_search_hint
import whatcampus.composeapp.generated.resources.university_title

@Composable
internal fun UniversitySelectivityScreen(
    modifier: Modifier = Modifier,
    viewModel: UniversityViewModel = koinViewModel<UniversityViewModel>(),
    onClickUniversity: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    val horizontalPadding = PaddingValues(horizontal = 20.dp)

    when (uiState) {
        UniversityUiState.Loading -> {
            // TODO: 로딩 화면 구현
        }

        is UniversityUiState.Success -> UniversitySelectivityScreen(
            modifier = modifier,
            horizontalPadding = horizontalPadding,
            uiState = uiState as UniversityUiState.Success,
            onClickUniversity = { university ->
                viewModel.selectUniversity(university)
                onClickUniversity()
            },
        )
    }

}

@Composable
private fun UniversitySelectivityScreen(
    modifier: Modifier = Modifier,
    horizontalPadding: PaddingValues,
    uiState: UniversityUiState.Success,
    onClickUniversity: (University) -> Unit,
) {
    var universityQuery by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(horizontalPadding)
    ) {
        Text(
            text = stringResource(Res.string.university_title),
            style = WhatcamTheme.typography.headlineMediumM,
            color = Mint01,
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = stringResource(Res.string.university_desc),
            style = WhatcamTheme.typography.bodyLargeR,
            color = Graphite,
        )

        Spacer(modifier = Modifier.padding(20.dp))


        SearchBar(
            value = universityQuery,
            onValueChange = { query -> universityQuery = query },
            hint = stringResource(Res.string.university_search_hint)
        )

        UniversityList(
            universities = uiState.universities,
            selectedUniversity = uiState.selectedUniversity,
            onClickUniversity = onClickUniversity,
        )
    }
}

