package com.talentland.talentlandappandroid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BeSoccerGreenPrimary = Color(0xFF2E7D32)
private val BeSoccerGreenSecondary = Color(0xFF4CAF50)
private val BeSoccerGreenTertiary = Color(0xFF1B5E20)
private val BeSoccerGreenDark = Color(0xFF1B5E20)
private val BeSoccerGreenLight = Color(0xFF66BB6A)

private val DarkColorScheme = darkColorScheme(
    primary = BeSoccerGreenSecondary,
    onPrimary = Color.White,
    primaryContainer = BeSoccerGreenTertiary,
    onPrimaryContainer = Color.White,
    secondary = BeSoccerGreenLight,
    onSecondary = Color.Black,
    secondaryContainer = BeSoccerGreenPrimary,
    onSecondaryContainer = Color.White,
    tertiary = BeSoccerGreenPrimary,
    onTertiary = Color.White,
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF101211),
    onBackground = Color(0xFFE1E3E0),
    surface = Color(0xFF101211),
    onSurface = Color(0xFFE1E3E0),
    surfaceVariant = Color(0xFF414941),
    onSurfaceVariant = Color(0xFFC1C9BE),
    outline = Color(0xFF8B9389),
    outlineVariant = Color(0xFF414941),
    scrim = Color.Black,
    inverseSurface = Color(0xFFE1E3E0),
    inverseOnSurface = Color(0xFF1F211F),
    inversePrimary = BeSoccerGreenPrimary,
    surfaceTint = Color.Gray
)

private val LightColorScheme = lightColorScheme(
    primary = BeSoccerGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = BeSoccerGreenDark,
    onPrimaryContainer = Color.White,
    secondary = BeSoccerGreenSecondary,
    onSecondary = Color.White,
    secondaryContainer = BeSoccerGreenLight,
    onSecondaryContainer = BeSoccerGreenPrimary,
    tertiary = BeSoccerGreenTertiary,
    onTertiary = Color.White,
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAFAFA),
    onBackground = Color(0xFF1F211F),
    surface = Color(0xFFFAFAFA),
    onSurface = Color(0xFF1F211F),
    surfaceVariant = Color(0xFFDFE5DB),
    onSurfaceVariant = Color(0xFF424940),
    outline = Color(0xFF73796F),
    outlineVariant = Color(0xFFC3C9BE),
    scrim = Color.Black,
    inverseSurface = Color(0xFF1F211F),
    inverseOnSurface = Color(0xFFEFF1ED),
    inversePrimary = BeSoccerGreenSecondary,
    surfaceTint = Color.White
)

@Composable
fun BeSoccerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}


