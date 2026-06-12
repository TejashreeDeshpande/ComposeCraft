package com.example.composecraft.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MidnightBackground = Color(0xFF050B18)
val CommandSurface = Color(0xFF0D1726)
val CommandCard = Color(0xFF132238)
val BorderGlow = Color(0xFF1F3A5F)

val ElectricBlue = Color(0xFF3B82F6)
val NeonCyan = Color(0xFF22D3EE)

val RescueGreen = Color(0xFF22C55E)
val EmergencyAmber = Color(0xFFF59E0B)
val AlertRed = Color(0xFFEF4444)
val SeismicPurple = Color(0xFFA855F7)

val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF94A3B8)
val TextDisabled = Color(0xFF475569)

// Incident colors
val FireColor = Color(0xFFFF5A36)
val FloodColor = Color(0xFF3B82F6)
val MedicalColor = Color(0xFFEF4444)
val PowerColor = Color(0xFFFACC15)
val EarthquakeColor = Color(0xFFA855F7)

private val DisasterDarkColorScheme = darkColorScheme(
    primary = ElectricBlue,
    secondary = NeonCyan,
    background = MidnightBackground,
    surface = CommandSurface,
    error = AlertRed,
    onPrimary = TextPrimary,
    onSecondary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = TextPrimary
)

@Composable
fun DisasterTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DisasterDarkColorScheme,
        typography = Typography,
        content = content
    )
}