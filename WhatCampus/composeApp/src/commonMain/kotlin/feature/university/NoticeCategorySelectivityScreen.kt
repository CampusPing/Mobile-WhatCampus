package feature.university

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import core.designsystem.components.NoticeCategoryList
import core.designsystem.theme.Graphite
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import core.designsystem.theme.White
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.department_title
import whatcampus.composeapp.generated.resources.notice_category_selectivity_desc
import whatcampus.composeapp.generated.resources.notice_category_selectivity_save
import whatcampus.composeapp.generated.resources.notice_category_selectivity_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoticeCategorySelectivityScreen(
    modifier: Modifier = Modifier,
    viewModel: UniversityViewModel,
    onClickSave: () -> Unit,
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateMultiplatform()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        onClickBack()
                        viewModel.searchDepartment("")
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.department_title),
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
                text = stringResource(Res.string.notice_category_selectivity_title),
                style = WhatcamTheme.typography.headlineMediumM,
                color = Mint01,
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = stringResource(Res.string.notice_category_selectivity_desc),
                style = WhatcamTheme.typography.bodyLargeR,
                color = Graphite,
            )

            Spacer(modifier = Modifier.padding(top = 40.dp))

            NoticeCategoryList(
                modifier = Modifier.weight(1f),
                noticeCategories = uiState.noticeCategories,
                subscribedNoticeCategories = uiState.selectedNoticeCategories,
                onClickCategory = viewModel::toggleNoticeCategory,
                isShowDescription = false,
            )

            Button(
                onClick = onClickSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = stringResource(Res.string.notice_category_selectivity_save),
                    style = WhatcamTheme.typography.bodyLargeB,
                    color = White,
                )
            }
        }
    }
}
