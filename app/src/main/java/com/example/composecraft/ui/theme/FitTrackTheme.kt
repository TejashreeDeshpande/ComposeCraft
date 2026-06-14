package com.example.composecraft.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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

val FitTrackPurple = Color(0xFFA45C99)
val FitTrackDarkPurple = Color(0xFF54234F)
val FitTrackDeepPurple = Color(0xFF7B3F76)

val FitTrackPink = Color(0xFFE86473)
val FitTrackCoral = Color(0xFFF0717B)
val FitTrackYellow = Color(0xFFFFF19A)

val FitTrackBackground = Color(0xFFF8F6F8)
val FitTrackSurface = Color(0xFFFFFFFF)
val FitTrackCard = Color(0xFFFFFFFF)
val FitTrackSoftCard = Color(0xFFF1F1F1)

val FitTrackTextPrimary = Color(0xFF2D2630)
val FitTrackTextSecondary = Color(0xFF6F6470)
val FitTrackTextLight = Color(0xFFFFFFFF)

val FitTrackSuccess = Color(0xFFA45C99)
val FitTrackDivider = Color(0xFFE8E0E8)


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
    error = FitTrackCoral
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