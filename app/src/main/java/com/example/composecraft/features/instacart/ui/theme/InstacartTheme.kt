package com.example.composecraft.features.instacart.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val InstacartGreen = Color(0xFF00A74A)
val InstacartGreenDark = Color(0xFF007A35)
val InstacartGreenLight = Color(0xFFE8F5ED)
val InstacartYellow = Color(0xFFFFC400)
val InstacartRed = Color(0xFFE53935)
val InstacartBg = Color(0xFFF6F6F6)
val InstacartSurface = Color(0xFFFFFFFF)
val InstacartTextPrimary = Color(0xFF1A1A1A)
val InstacartTextSecondary = Color(0xFF666666)
val InstacartDivider = Color(0xFFEEEEEE)
val InstacartBadge = Color(0xFFFF5722)

private val LightColors = lightColorScheme(
    primary = InstacartGreen,
    onPrimary = Color.White,
    primaryContainer = InstacartGreenLight,
    onPrimaryContainer = InstacartGreenDark,
    secondary = InstacartYellow,
    onSecondary = InstacartTextPrimary,
    background = InstacartBg,
    onBackground = InstacartTextPrimary,
    surface = InstacartSurface,
    onSurface = InstacartTextPrimary,
    error = InstacartRed,
    onError = Color.White
)

@Composable
fun InstacartTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, content = content)
}
