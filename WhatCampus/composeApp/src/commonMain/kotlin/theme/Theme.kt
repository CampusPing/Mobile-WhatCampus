package theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val WhatcamThemeDarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Mint01,
    primaryContainer = Graphite,
    onPrimaryContainer = White,
    inversePrimary = Green03,
    secondary = Green04,
    onSecondary = Green01,
    secondaryContainer = Green04,
    onSecondaryContainer = White,
    tertiary = Yellow05,
    onTertiary = Yellow01,
    tertiaryContainer = Yellow04,
    onTertiaryContainer = White,
    error = Red02,
    onError = Red05,
    errorContainer = Red04,
    onErrorContainer = Red01,
    surface = Graphite,
    onSurface = White,
    onSurfaceVariant = White,
    surfaceDim = Black,
    surfaceContainerHigh = DuskGray,
    inverseSurface = Mint05,
    inverseOnSurface = Black,
    outline = DarkGray,
    outlineVariant = Cosmos,
    scrim = Black,
)

private val WhatcamThemeLightColorScheme = lightColorScheme(
    primary = Mint01,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = Black,
    inversePrimary = Mint01,
    secondary = Green04,
    onSecondary = White,
    secondaryContainer = Green01,
    onSecondaryContainer = Green04,
    tertiary = Yellow01,
    onTertiary = Black,
    tertiaryContainer = Yellow03A40,
    onTertiaryContainer = Yellow04,
    error = Red03,
    onError = White,
    errorContainer = Red01,
    onErrorContainer = Red06,
    surface = PaperGray,
    onSurface = DuskGray,
    onSurfaceVariant = DarkGray,
    surfaceDim = PaleGray,
    surfaceContainerHigh = LightGray,
    inverseSurface = Yellow05,
    inverseOnSurface = White,
    outline = LightGray,
    outlineVariant = DarkGray,
    scrim = Black,
)

@Composable
fun WhatcamTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) WhatcamThemeDarkColorScheme else WhatcamThemeLightColorScheme

    CompositionLocalProvider(
        LocalTypography provides Typography,
    ) {
        MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            content = content,
        )
    }
}
