package com.example.composecraft.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

enum class FontSize(val value: androidx.compose.ui.unit.TextUnit) {

    EXTRA_SMALL(10.sp),
    SMALL(12.sp),
    MEDIUM(14.sp),
    BODY(16.sp),
    TITLE(20.sp),
    HEADER(24.sp),
    LARGE_HEADER(32.sp)
}

val FitTrackPurple = Color(0xFF9155A5)
val FitTrackDarkPurple = Color(0xFF572C67)
val FitTrackPink = Color(0xFFC97AA4)
val FitTrackCoral = Color(0xFFE26972)
val FitTrackYellow = Color(0xFFE6D983)

val FitTrackBackground = Color(0xFFF5F5F5)
val FitTrackSurface = Color(0xFFFFFFFF)
val FitTrackSoftCard = Color(0xFFF7F1F8)

val FitTrackTextPrimary = Color(0xFF2D2D2D)
val FitTrackTextSecondary = Color(0xFF757575)
val FitTrackTextLight = Color(0xFFFFFFFF)

val FitTrackDivider = Color(0xFFE3E3E3)

// Gradients
val FitTrackHeaderGradient = listOf(
    FitTrackDarkPurple,
    FitTrackPurple,
    FitTrackPink
)

val FitTrackWarmGradient = listOf(
    FitTrackYellow,
    FitTrackCoral
)

val FitTrackProgressGradient = listOf(
    FitTrackPink,
    FitTrackCoral
)

private val FitTrackColorScheme = lightColorScheme(

    primary = FitTrackPurple,
    onPrimary = FitTrackTextLight,

    secondary = FitTrackPink,
    onSecondary = FitTrackTextLight,

    tertiary = FitTrackYellow,
    onTertiary = FitTrackTextPrimary,

    background = FitTrackBackground,
    onBackground = FitTrackTextPrimary,

    surface = FitTrackSurface,
    onSurface = FitTrackTextPrimary,

    surfaceVariant = FitTrackSoftCard,
    onSurfaceVariant = FitTrackTextSecondary,

    outline = FitTrackDivider,

    error = FitTrackCoral,
    onError = FitTrackTextLight
)


val FitTrackTypography = Typography(

    headlineMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),

    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),

    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),

    bodyLarge = TextStyle(
        fontSize = 14.sp
    ),

    bodyMedium = TextStyle(
        fontSize = 12.sp
    ),

    labelMedium = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )
)

@Composable
fun FitTrackTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FitTrackColorScheme,
        typography = Typography,
        content = content
    )
}

object FitTrackGradients {

    val Dashboard = Brush.horizontalGradient(
        listOf(
            FitTrackDarkPurple,
            FitTrackPurple,
            FitTrackPink
        )
    )

    val WorkoutBuilder = Brush.horizontalGradient(
        listOf(
            FitTrackYellow,
            Color(0xFFE8B07A),
            FitTrackCoral
        )
    )

    val WorkoutSession = Brush.horizontalGradient(
        listOf(
            FitTrackPink,
            FitTrackCoral
        )
    )

    val ExerciseLibrary = Brush.horizontalGradient(
        listOf(
            Color(0xFFD06D92),
            FitTrackPink,
            FitTrackPurple
        )
    )

    val Profile = Brush.horizontalGradient(
        listOf(
            FitTrackPurple.copy(alpha = 0.7f),
            FitTrackPink.copy(alpha = 0.6f),
            FitTrackYellow
        )
    )
}