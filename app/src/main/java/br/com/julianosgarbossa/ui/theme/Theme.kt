package br.com.julianosgarbossa.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = LibraryPrimaryDark,
    onPrimary = LibraryOnPrimaryDark,
    primaryContainer = LibraryPrimaryContainerDark,
    onPrimaryContainer = LibraryOnPrimaryContainerDark,
    secondary = LibrarySecondaryDark,
    onSecondary = LibraryOnSecondaryDark,
    secondaryContainer = LibrarySecondaryContainerDark,
    onSecondaryContainer = LibraryOnSecondaryContainerDark,
    tertiary = LibraryTertiaryDark,
    onTertiary = LibraryOnTertiaryDark,
    background = LibraryBackgroundDark,
    surface = LibrarySurfaceDark,
    surfaceVariant = LibrarySurfaceVariantDark,
    onSurface = LibraryOnSurfaceDark,
    outline = LibraryOutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = LibraryPrimaryLight,
    onPrimary = LibraryOnPrimaryLight,
    primaryContainer = LibraryPrimaryContainerLight,
    onPrimaryContainer = LibraryOnPrimaryContainerLight,
    secondary = LibrarySecondaryLight,
    onSecondary = LibraryOnSecondaryLight,
    secondaryContainer = LibrarySecondaryContainerLight,
    onSecondaryContainer = LibraryOnSecondaryContainerLight,
    tertiary = LibraryTertiaryLight,
    onTertiary = LibraryOnTertiaryLight,
    background = LibraryBackgroundLight,
    surface = LibrarySurfaceLight,
    surfaceVariant = LibrarySurfaceVariantLight,
    onSurface = LibraryOnSurfaceLight,
    outline = LibraryOutlineLight
)

@Composable
fun MyLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}