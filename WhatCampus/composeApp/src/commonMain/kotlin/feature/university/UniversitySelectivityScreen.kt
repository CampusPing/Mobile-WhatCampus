package feature.university

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Graphite
import core.designsystem.theme.Mint01
import core.designsystem.theme.WhatcamTheme
import feature.university.components.SearchBar
import feature.university.components.UniversityList
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.university_desc
import whatcampus.composeapp.generated.resources.university_search_hint
import whatcampus.composeapp.generated.resources.university_title

@Composable
internal fun UniversitySelectivityScreen(
    modifier: Modifier = Modifier,
) {
    val horizontalPadding = PaddingValues(horizontal = 20.dp)
    val (universityQuery, setUniversityQuery) = mutableStateOf("")

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
            onValueChange = setUniversityQuery,
            hint = stringResource(Res.string.university_search_hint)
        )

        UniversityList(
            onClickUniversity = {}
        )
    }
}

