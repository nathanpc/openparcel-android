package com.innoveworkshop.openparcel.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat

/**
 * Creates a copy of a color while changing only its hue.
 *
 * @param hue New color's hue value.
 *
 * @return Same color with a different hue.
 */
fun Color.copyHue(hue: Float): Color {
    val oldHsl = FloatArray(3)
    ColorUtils.colorToHSL(this.toArgb(), oldHsl)

    return Color.hsl(
        hue = hue,
        saturation = oldHsl[1],
        lightness = oldHsl[2],
        alpha = this.alpha
    )
}

/**
 * Creates a copy of a color scheme while changing the hue of all of the colors in it.
 *
 * @param hue New hue value of all the colors in the color scheme.
 *
 * @return Same color scheme but with a different global hue.
 */
fun ColorScheme.copyHue(hue: Float): ColorScheme = this.copy(
    primary = primary.copyHue(hue),
    onPrimary = onPrimary.copyHue(hue),
    primaryContainer = primaryContainer.copyHue(hue),
    onPrimaryContainer = onPrimaryContainer.copyHue(hue),
    secondary = secondary.copyHue(hue),
    onSecondary = onSecondary.copyHue(hue),
    secondaryContainer = secondaryContainer.copyHue(hue),
    onSecondaryContainer = onSecondaryContainer.copyHue(hue),
    tertiary = tertiary.copyHue(hue),
    onTertiary = onTertiary.copyHue(hue),
    tertiaryContainer = tertiaryContainer.copyHue(hue),
    onTertiaryContainer = onTertiaryContainer.copyHue(hue),
    error = error.copyHue(hue),
    onError = onError.copyHue(hue),
    errorContainer = errorContainer.copyHue(hue),
    onErrorContainer = onErrorContainer.copyHue(hue),
    background = background.copyHue(hue),
    onBackground = onBackground.copyHue(hue),
    surface = surface.copyHue(hue),
    onSurface = onSurface.copyHue(hue),
    surfaceVariant = surfaceVariant.copyHue(hue),
    onSurfaceVariant = onSurfaceVariant.copyHue(hue),
    outline = outline.copyHue(hue),
    outlineVariant = outlineVariant.copyHue(hue),
    scrim = scrim.copyHue(hue),
    inverseSurface = inverseSurface.copyHue(hue),
    inverseOnSurface = inverseOnSurface.copyHue(hue),
    inversePrimary = inversePrimary.copyHue(hue),
    surfaceDim = surfaceDim.copyHue(hue),
    surfaceBright = surfaceBright.copyHue(hue),
    surfaceContainerLowest = surfaceContainerLowest.copyHue(hue),
    surfaceContainerLow = surfaceContainerLow.copyHue(hue),
    surfaceContainer = surfaceContainer.copyHue(hue),
    surfaceContainerHigh = surfaceContainerHigh.copyHue(hue),
    surfaceContainerHighest = surfaceContainerHighest.copyHue(hue),
)

@Immutable
data class ExtendedColorScheme(
    val blue: ColorFamily,
    val green: ColorFamily
) {
    /**
     * Gets the extended color scheme by its name.
     *
     * @param name Name of the extended color scheme.
     *
     * @return Extended color scheme family.
     *
     * @throws NoSuchElementException if the name does not correspond to any color scheme.
     */
    fun getScheme(name: String): ColorFamily = when (name) {
        "blue" -> blue
        "green" -> green
        else -> throw NoSuchElementException("Extended color $name does not currently exist")
    }
}

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
    blue = ColorFamily(
        blueLight,
        onBlueLight,
        blueContainerLight,
        onBlueContainerLight,
    ),
    green = ColorFamily(
        greenLight,
        onGreenLight,
        greenContainerLight,
        onGreenContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    blue = ColorFamily(
        blueDark,
        onBlueDark,
        blueContainerDark,
        onBlueContainerDark,
    ),
    green = ColorFamily(
        greenDark,
        onGreenDark,
        greenContainerDark,
        onGreenContainerDark,
    ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
    blue = ColorFamily(
        blueLightMediumContrast,
        onBlueLightMediumContrast,
        blueContainerLightMediumContrast,
        onBlueContainerLightMediumContrast,
    ),
    green = ColorFamily(
        greenLightMediumContrast,
        onGreenLightMediumContrast,
        greenContainerLightMediumContrast,
        onGreenContainerLightMediumContrast,
    ),
)

val extendedLightHighContrast = ExtendedColorScheme(
    blue = ColorFamily(
        blueLightHighContrast,
        onBlueLightHighContrast,
        blueContainerLightHighContrast,
        onBlueContainerLightHighContrast,
    ),
    green = ColorFamily(
        greenLightHighContrast,
        onGreenLightHighContrast,
        greenContainerLightHighContrast,
        onGreenContainerLightHighContrast,
    ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
    blue = ColorFamily(
        blueDarkMediumContrast,
        onBlueDarkMediumContrast,
        blueContainerDarkMediumContrast,
        onBlueContainerDarkMediumContrast,
    ),
    green = ColorFamily(
        greenDarkMediumContrast,
        onGreenDarkMediumContrast,
        greenContainerDarkMediumContrast,
        onGreenContainerDarkMediumContrast,
    ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
    blue = ColorFamily(
        blueDarkHighContrast,
        onBlueDarkHighContrast,
        blueContainerDarkHighContrast,
        onBlueContainerDarkHighContrast,
    ),
    green = ColorFamily(
        greenDarkHighContrast,
        onGreenDarkHighContrast,
        greenContainerDarkHighContrast,
        onGreenContainerDarkHighContrast,
    ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

/**
 * Gets the base color scheme given the current environment.
 *
 * @param darkTheme    Should we use a dark theme?
 * @param dynamicColor Should we use the Material You dynamic color thing?
 *
 * @return Application color scheme appropriate for the current environment.
 */
@Composable
fun getBaseColorScheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false
): ColorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> darkScheme
    else -> lightScheme
}

/**
 * Fixes some problems related to theming in the developer tools.
 *
 * @param colorScheme Current color scheme of the application.
 * @param darkTheme   Should we use a dark theme?
 */
@Composable
fun DeveloperModeFix(
    colorScheme: ColorScheme,
    darkTheme: Boolean
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = getBaseColorScheme(darkTheme, dynamicColor)
    DeveloperModeFix(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun ExtendedTheme(
    colorName: String,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Substitute with extended colors.
    val colorScheme = when {
        darkTheme -> getBaseColorScheme(true, dynamicColor).copy(
            primary = extendedDark.getScheme(colorName).color,
            onPrimary = extendedDark.getScheme(colorName).onColor,
            primaryContainer = extendedDark.getScheme(colorName).colorContainer,
            onPrimaryContainer = extendedDark.getScheme(colorName).onColorContainer,
            surfaceVariant = extendedDark.getScheme(colorName).colorContainer,
            onSurfaceVariant = extendedLight.getScheme(colorName).onColorContainer
        )

        else -> getBaseColorScheme(false, dynamicColor).copy(
            primary = extendedLight.getScheme(colorName).color,
            onPrimary = extendedLight.getScheme(colorName).onColor,
            primaryContainer = extendedLight.getScheme(colorName).colorContainer,
            onPrimaryContainer = extendedLight.getScheme(colorName).onColorContainer,
            surfaceVariant = extendedLight.getScheme(colorName).colorContainer,
            onSurfaceVariant = extendedLight.getScheme(colorName).onColorContainer
        )
    }

    // Fix development tools.
    DeveloperModeFix(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun HueBasedTheme(
    hue: Float,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Substitute with extended colors.
    val colorScheme = when {
        darkTheme -> darkScheme.copyHue(hue)
        else -> lightScheme.copyHue(hue)
    }

    // Fix development tools.
    DeveloperModeFix(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
