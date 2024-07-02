package feature.university

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import core.model.Department
import feature.university.components.DepartmentList
import feature.university.components.SearchBar
import feature.university.model.UniversityUiState
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.department_desc
import whatcampus.composeapp.generated.resources.department_search_hint
import whatcampus.composeapp.generated.resources.department_title
import whatcampus.composeapp.generated.resources.university_title

@Composable
internal fun DepartmentSelectivityScreen(
    modifier: Modifier = Modifier,
    viewModel: UniversityViewModel,
    onClickDepartment: () -> Unit,
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

    DepartmentSelectivityScreen(
        modifier = modifier,
        uiState = uiState,
        searchQuery = uiState.departmentSearchQuery,
        onSearchQueryChange = viewModel::searchDepartment,
        onClickDepartment = { department ->
            viewModel.selectDepartment(department)
            onClickDepartment()
        },
        onClickBack = {
            onClickBack()
            viewModel.searchUniversity("")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DepartmentSelectivityScreen(
    modifier: Modifier = Modifier,
    uiState: UniversityUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onClickDepartment: (Department) -> Unit,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.university_title),
                            tint = Graphite,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(Res.string.department_title),
                style = WhatcamTheme.typography.headlineMediumM,
                color = Mint01,
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = stringResource(Res.string.department_desc),
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
            )

            Spacer(modifier = Modifier.padding(top = 40.dp))

            SearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                hint = stringResource(Res.string.department_search_hint)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            DepartmentList(
                departments = uiState.filteredDepartments,
                selectedDepartment = uiState.selectedDepartment,
                onClickDepartment = onClickDepartment,
            )
        }
    }
}
