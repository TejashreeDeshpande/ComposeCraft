package com.example.composecraft.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * ==========================================================
 * Flight Status Theme
 * ==========================================================
 */

// Primary Aviation Colors
val AviationBlue = Color(0xFF1565C0)
val SkyBlue = Color(0xFF42A5F5)
val SuccessGreen = Color(0xFF00C853)
val WarningOrange = Color(0xFFFF9800)
val ErrorRed = Color(0xFFD32F2F)

val FlightBackground = Color(0xFFF5F9FF)
val FlightSurface = Color(0xFFFFFFFF)
val FlightSurfaceVariant = Color(0xFFE8F1FF)

val FlightTextPrimary = Color(0xFF1A1A1A)
val FlightTextSecondary = Color(0xFF616161)

/**
 * ==========================================================
 * Status Colors
 * ==========================================================
 */

object FlightStatusColors {
    val Scheduled = Color(0xFF607D8B)
    val CheckIn = Color(0xFF5E35B1)
    val Security = Color(0xFF3949AB)
    val Boarding = Color(0xFF1565C0)
    val Departed = Color(0xFF00ACC1)
    val InAir = Color(0xFF7B1FA2)
    val Landing = Color(0xFFFF9800)
    val Landed = Color(0xFF00C853)
    val Delayed = Color(0xFFFF9800)
    val Cancelled = Color(0xFFD32F2F)
    val OnTime = Color(0xFF00C853)
}

/**
 * ==========================================================
 * Gradients
 * ==========================================================
 */

object FlightGradients {

    val Dashboard = Brush.horizontalGradient(
        listOf(
            Color(0xFF4FACFE),
            Color(0xFF00F2FE)
        )
    )

    val FlightDetails = Brush.horizontalGradient(
        listOf(
            Color(0xFF1565C0),
            Color(0xFF42A5F5)
        )
    )

    val AirportMap = Brush.horizontalGradient(
        listOf(
            Color(0xFF00C853),
            Color(0xFF64DD17)
        )
    )

    val BoardingPass = Brush.horizontalGradient(
        listOf(
            Color(0xFF6A11CB),
            Color(0xFF2575FC)
        )
    )

    val Timeline = Brush.horizontalGradient(
        listOf(
            Color(0xFFFF9966),
            Color(0xFFFF5E62)
        )
    )
}

/**
 * ==========================================================
 * Material 3 Color Scheme
 * ==========================================================
 */

private val FlightColorScheme = lightColorScheme(
    primary = AviationBlue,
    onPrimary = Color.White,

    secondary = SkyBlue,
    onSecondary = Color.White,

    tertiary = SuccessGreen,
    onTertiary = Color.White,

    background = FlightBackground,
    onBackground = FlightTextPrimary,

    surface = FlightSurface,
    onSurface = FlightTextPrimary,

    surfaceVariant = FlightSurfaceVariant,
    onSurfaceVariant = FlightTextSecondary,

    error = ErrorRed,
    onError = Color.White
)

private val DarkFlightColorScheme = darkColorScheme(
    primary = SkyBlue,
    onPrimary = Color.Black,

    secondary = AviationBlue,
    onSecondary = Color.White,

    tertiary = SuccessGreen,
    onTertiary = Color.Black,

    background = Color(0xFF0D1117),
    onBackground = Color.White,

    surface = Color(0xFF161B22),
    onSurface = Color.White,

    surfaceVariant = Color(0xFF21262D),
    onSurfaceVariant = Color(0xFF8B949E),

    error = ErrorRed,
    onError = Color.White
)

/**
 * ==========================================================
 * Theme
 * ==========================================================
 */

@Composable
fun FlightStatusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkFlightColorScheme else FlightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

/**
 * ==========================================================
 * Flight Status Enum
 * ==========================================================
 */

enum class FlightStatus(
    val title: String,
    val color: Color
) {
    SCHEDULED(
        "Scheduled",
        FlightStatusColors.Scheduled
    ),

    CHECK_IN(
        "Check-In",
        FlightStatusColors.CheckIn
    ),

    SECURITY(
        "Security",
        FlightStatusColors.Security
    ),

    BOARDING(
        "Boarding",
        FlightStatusColors.Boarding
    ),

    DEPARTED(
        "Departed",
        FlightStatusColors.Departed
    ),

    IN_AIR(
        "In Air",
        FlightStatusColors.InAir
    ),

    LANDING(
        "Landing",
        FlightStatusColors.Landing
    ),

    LANDED(
        "Landed",
        FlightStatusColors.Landed
    ),

    DELAYED(
        "Delayed",
        FlightStatusColors.Delayed
    ),

    CANCELLED(
        "Cancelled",
        FlightStatusColors.Cancelled
    )
}