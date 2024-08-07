package feature.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designsystem.theme.DarkGray
import core.designsystem.theme.Graphite
import core.designsystem.theme.WhatcamTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whatcampus.composeapp.generated.resources.Res
import whatcampus.composeapp.generated.resources.img_default_profile
import whatcampus.composeapp.generated.resources.profile_image

@Composable
internal fun UserInformation(
    universityName: String,
    departmentName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            painter = painterResource(Res.drawable.img_default_profile),
            contentDescription = stringResource(Res.string.profile_image),
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = universityName,
                style = WhatcamTheme.typography.bodyLargeB,
                color = Graphite,
            )

            Text(
                text = departmentName,
                style = WhatcamTheme.typography.bodyLargeR,
                color = DarkGray,
            )
        }
    }
}
