package com.example.composecraft.features.pulseinvest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PulseGreen = Color(0xFF00FF88)
val PulseGreenDim = Color(0xFF00CC66)
val PulseRed = Color(0xFFFF4444)
val PulseDark = Color(0xFF111111)
val PulseSurface = Color(0xFF1A1A1A)
val PulseSurface2 = Color(0xFF1E1E1E)
val PulseBorder = Color(0xFF2A2A2A)
val PulseTextPrimary = Color(0xFFFFFFFF)
val PulseTextSecondary = Color(0xFF888888)
val PulseTextMuted = Color(0xFF555555)
val PulseGreenBg = Color(0xFF1E2E24)

private val PulseColorScheme = darkColorScheme(
    primary = PulseGreen,
    onPrimary = Color.Black,
    secondary = PulseGreenDim,
    background = PulseDark,
    surface = PulseSurface,
    onBackground = PulseTextPrimary,
    onSurface = PulseTextPrimary,
    error = PulseRed
)

@Composable
fun PulseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PulseColorScheme,
        content = content
    )
}
