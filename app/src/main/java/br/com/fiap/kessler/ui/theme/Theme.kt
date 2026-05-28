package br.com.fiap.kessler.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val KesslerColorScheme = darkColorScheme(
    primary = MarsOrange,
    onPrimary = SpaceBlack,
    background = SpaceBlack,
    onBackground = TextPrimary,
    surface = SpaceSurface,
    onSurface = TextPrimary,
    surfaceVariant = SpaceSurfaceElevated,
    onSurfaceVariant = TextSecondary,
    outline = SpaceBorder
)

@Composable
fun KesslerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = KesslerColorScheme,
        typography = KesslerTypography,
        content = content
    )
}
