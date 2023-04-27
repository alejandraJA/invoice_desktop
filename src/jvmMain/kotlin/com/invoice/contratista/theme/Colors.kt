package com.invoice.contratista.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val graySurface = Color(0xbb3e03)
val lightGray = Color(0xFFD3D3D3)
val lightOrange = Color(0xE37E5E)
val orange = Color(0xFF5722)
val darkBackground = Color(0xff100c08)
val darkGray = Color(0xFF565656)
val spotifyGradient = listOf(lightOrange, Color.Yellow, lightOrange.copy(alpha = 0.8f))

val DarkGreenColorPalette = darkColors(
    primary = orange,
    primaryVariant = orange,
    secondary = graySurface,
    background = darkBackground,
    surface = darkBackground,
    onPrimary = darkBackground,
    onSecondary = lightGray,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

val LightGreenColorPalette = lightColors(
    primary = orange,
    primaryVariant = orange,
    secondary = lightGray,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = graySurface,
    onBackground = darkBackground,
    onSurface = darkBackground
)