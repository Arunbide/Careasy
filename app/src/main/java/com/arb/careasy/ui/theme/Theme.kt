package com.arb.careasy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Dark color scheme (since your app has black background)
private val DarkColorScheme = darkColorScheme(
    primary = AppColors.Blue,
    secondary = AppColors.Grey20,
    tertiary = AppColors.Grey40,
    background = AppColors.Black,
    surface = AppColors.Black,
    onPrimary = AppColors.White,
    onSecondary = AppColors.White,
    onTertiary = AppColors.White,
    onBackground = AppColors.White,
    onSurface = AppColors.White,
)

// Light color scheme (optional, but good to have)
private val LightColorScheme = lightColorScheme(
    primary = AppColors.Blue,
    secondary = AppColors.Grey60,
    tertiary = AppColors.Grey40,
    background = AppColors.White,
    surface = AppColors.White,
    onPrimary = AppColors.White,
    onSecondary = AppColors.Black,
    onTertiary = AppColors.Black,
    onBackground = AppColors.Black,
    onSurface = AppColors.Black,
)

@Composable
fun CareasyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}