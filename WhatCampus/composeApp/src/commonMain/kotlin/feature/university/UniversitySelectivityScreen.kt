package feature.university

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.common.extensions.collectAsStateMultiplatform
import core.designsystem.theme.Graphite
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
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
    viewModel: UniversityViewModel,
    onClickUniversity: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()
    val universityQuery by viewModel.universitySearchQuery.collectAsStateMultiplatform()

    UniversitySelectivityScreen(
        modifier = modifier,
        uiState = uiState,
        searchQuery = universityQuery,
        onSearchQueryChange = viewModel::searchUniversity,
        onClickUniversity = { university ->
            viewModel.selectUniversity(university)
            onClickUniversity()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UniversitySelectivityScreen(
    modifier: Modifier = Modifier,
    uiState: UniversityUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onClickUniversity: (University) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(Res.string.university_title),
                style = WhatcamTheme.typography.headlineMediumM,
                color = Mint01,
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = stringResource(Res.string.university_desc),
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
            )

            Spacer(modifier = Modifier.padding(top = 40.dp))

            SearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                hint = stringResource(Res.string.university_search_hint)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            UniversityList(
                universities = uiState.universities,
                selectedUniversity = uiState.selectedUniversity,
                onClickUniversity = onClickUniversity,
            )
        }
    }
}

