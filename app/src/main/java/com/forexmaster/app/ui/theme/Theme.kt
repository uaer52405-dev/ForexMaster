package com.forexmaster.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cuba-inspired palette
val CubaBlue        = Color(0xFF0B3D8C)
val CubaBlueDark    = Color(0xFF072966)
val CubaBlueLight   = Color(0xFF3A6FD8)
val CubaBlueContainer = Color(0xFFD6E4FF)
val CubaRed         = Color(0xFFCF0A0A)
val CubaRedLight    = Color(0xFFFF4D4D)
val CubaRedContainer = Color(0xFFFFDAD6)
val CubaGold        = Color(0xFFF5A623)
val White           = Color(0xFFFFFFFF)
val OffWhite        = Color(0xFFF5F7FF)
val DarkNavy        = Color(0xFF0D1B40)
val MediumGray      = Color(0xFF6B7280)
val LightGray       = Color(0xFFEEF1F8)
val SuccessGreen    = Color(0xFF27AE60)
val SuccessContainer = Color(0xFFD4F5E4)

private val LightColorScheme = lightColorScheme(
    primary            = CubaBlue,
    onPrimary          = White,
    primaryContainer   = CubaBlueContainer,
    onPrimaryContainer = CubaBlueDark,
    secondary          = CubaRed,
    onSecondary        = White,
    secondaryContainer = CubaRedContainer,
    onSecondaryContainer = CubaRed,
    tertiary           = CubaGold,
    onTertiary         = White,
    background         = OffWhite,
    onBackground       = DarkNavy,
    surface            = White,
    onSurface          = DarkNavy,
    surfaceVariant     = LightGray,
    onSurfaceVariant   = MediumGray,
    outline            = Color(0xFFBDBDBD),
    outlineVariant     = Color(0xFFE0E0E0),
    error              = CubaRed,
    onError            = White
)

private val DarkColorScheme = darkColorScheme(
    primary            = CubaBlueLight,
    onPrimary          = CubaBlueDark,
    primaryContainer   = CubaBlue,
    onPrimaryContainer = CubaBlueContainer,
    secondary          = CubaRedLight,
    onSecondary        = CubaBlueDark,
    secondaryContainer = Color(0xFF5C0000),
    onSecondaryContainer = CubaRedLight,
    background         = Color(0xFF0D1420),
    onBackground       = White,
    surface            = Color(0xFF1A243A),
    onSurface          = White,
    surfaceVariant     = Color(0xFF253050),
    onSurfaceVariant   = Color(0xFFB0BDD8),
    outline            = Color(0xFF374561)
)

@Composable
fun ForexMasterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = MaterialTheme.typography,
        content     = content
    )
}
