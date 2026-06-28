package com.example.composecraft.features.fittrack.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp

fun Modifier.drawLeftCurvedBorder(
    borderWidth: Dp,
    borderColor: Color,
    cardRadius: Dp
): Modifier = this.drawBehind {
    val widthPx = borderWidth.toPx()
    val radiusPx = cardRadius.toPx()

    // Create a path matching the rounded card bounds
    val clipPath = Path().apply {
        addRoundRect(
            RoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                cornerRadius = CornerRadius(radiusPx)
            )
        )
    }

    // Clip the drawing scope and render the rounded edge
    clipPath(clipPath) {
        drawRoundRect(
            color = borderColor,
            topLeft = Offset(0f, 0f),
            size = Size(widthPx * 2, size.height), // Double width to hide the right edge
            cornerRadius = CornerRadius(radiusPx)
        )
    }
}
