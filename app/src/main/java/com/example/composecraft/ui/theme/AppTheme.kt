package com.example.composecraft.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object AppTheme {

    private val lightModeColors = AppColors(
        background = Color.White,
        onBackground = Color.Black,
        accent = Color(0xFF0061A4),
        onAccent = Color.White,
        cardContainer = Color(red = 231, green = 224, blue = 236),
        onCardContainer = Color(red = 73, green = 69, blue = 79),
        textPrimary = Color(0xFF1C1B1F),
        textSecondary = Color(0xFF49454F),
        danger = Color(0xFFBA1A1A),
        onDanger = Color.White,
        divider = Color(0xFFCAC4D0),
    )

    private val darkModeColors = AppColors(
        background = Color.Black,
        onBackground = Color.White,
        accent = Color(0xFF9CCAFF),
        onAccent = Color(0xFF003257),
        cardContainer = Color(red = 73, green = 69, blue = 79),
        onCardContainer = Color(red = 202, green = 196, blue = 208),
        textPrimary = Color(0xFFE6E1E5),
        textSecondary = Color(0xFFCAC4D0),
        danger = Color(0xFFFFB4AB),
        onDanger = Color(0xFF690005),
        divider = Color(0xFF49454F),
    )

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColor.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = AppTypography(
            title = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold),
            body = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
            label = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium),
        )

    val sizes: AppSizes
        @Composable
        @ReadOnlyComposable
        get() = AppSizes(
            s = 4.dp,
            m = 8.dp,
            l = 12.dp,
            xl = 16.dp,
            xxl = 24.dp
        )

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = AppShapes(
            container = RoundedCornerShape(12.dp),
            pill = RoundedCornerShape(percent = 50),
        )

    @Composable
    operator fun invoke(
        isDarkMode: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
    ) {
        val colors = if (isDarkMode) darkModeColors else lightModeColors
        CompositionLocalProvider(
            LocalAppColor provides colors,
            LocalAppTypography provides typography,
            LocalAppSize provides sizes,
            LocalAppShapes provides shapes,
            content = content,
        )
    }
}

@Immutable
data class AppColors(
    val background: Color,
    val onBackground: Color,
    val accent: Color,
    val onAccent: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val cardContainer: Color,
    val onCardContainer: Color,
    val danger: Color,
    val onDanger: Color,
    val divider: Color,
)

val LocalAppColor = staticCompositionLocalOf {
    AppColors(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        accent = Color.Unspecified,
        onAccent = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        cardContainer = Color.Unspecified,
        onCardContainer = Color.Unspecified,
        danger = Color.Unspecified,
        onDanger = Color.Unspecified,
        divider = Color.Unspecified,
    )
}

@Immutable
data class AppTypography(
    val title: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        title = TextStyle.Default,
        body = TextStyle.Default,
        label = TextStyle.Default,
    )
}

@Immutable
data class AppSizes(
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
    val xxl: Dp,
)

val LocalAppSize = staticCompositionLocalOf {
    AppSizes(
        s = Dp.Unspecified,
        m = Dp.Unspecified,
        l = Dp.Unspecified,
        xl = Dp.Unspecified,
        xxl = Dp.Unspecified,
    )
}

@Immutable
data class AppShapes(
    val container: CornerBasedShape,
    val pill: CornerBasedShape,
)

val LocalAppShapes = staticCompositionLocalOf {
    AppShapes(
        container = RoundedCornerShape(
            ZeroCornerSize,
            ZeroCornerSize,
            ZeroCornerSize,
            ZeroCornerSize,
        ),
        pill = RoundedCornerShape(percent = 50),
    )
}
