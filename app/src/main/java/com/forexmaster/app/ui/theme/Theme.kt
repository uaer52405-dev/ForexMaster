package com.forexmaster.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ForexBlue = Color(0xFF1565C0)
private val ForexBlueDark = Color(0xFF0D47A1)
private val ForexBlueLight = Color(0xFF42A5F5)
private val ForexBlueContainer = Color(0xFFD1E4FF)
private val White = Color(0xFFFFFFFF)
private val LightGray = Color(0xFFF5F5F5)
private val DarkText = Color(0xFF1A1A2E)
private val MediumGray = Color(0xFF6B7280)

private val LightColorScheme = lightColorScheme(
    primary = ForexBlue,
    onPrimary = White,
    primaryContainer = ForexBlueContainer,
    onPrimaryContainer = ForexBlueDark,
    secondary = ForexBlueLight,
    onSecondary = White,
    secondaryContainer = ForexBlueContainer,
    onSecondaryContainer = ForexBlueDark,
    background = White,
    onBackground = DarkText,
    surface = White,
    onSurface = DarkText,
    surfaceVariant = LightGray,
    onSurfaceVariant = MediumGray,
    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFFE0E0E0)
)

private val DarkColorScheme = darkColorScheme(
    primary = ForexBlueLight,
    onPrimary = ForexBlueDark,
    primaryContainer = ForexBlue,
    onPrimaryContainer = ForexBlueContainer,
    secondary = ForexBlueLight,
    onSecondary = ForexBlueDark,
    background = Color(0xFF121212),
    onBackground = White,
    surface = Color(0xFF1E1E1E),
    onSurface = White,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFB0B0B0)
)

@Composable
fun ForexMasterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
