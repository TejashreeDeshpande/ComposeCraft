package com.example.composecraft.features.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sin

@Preview
@Composable
fun PreviewWaveProgressDemo() {
    MaterialTheme {
        WaveProgressDemo()
    }
}
@Composable
fun WaveProgressDemo() {
    val transition = rememberInfiniteTransition(label = "wave")
    val waveColor = MaterialTheme.colorScheme.primary

    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(1400, easing = LinearEasing)
        ),
        label = "phase"
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(24.dp)
    ) {
        val progress = 0.65f
        val waveHeight = 24f
        val waterLevel = size.height * (1f - progress)

        val path = Path().apply {
            moveTo(0f, waterLevel)

            for (x in 0..size.width.toInt()) {
                val y = waterLevel + sin((x + phase) * 0.04f) * waveHeight
                lineTo(x.toFloat(), y)
            }

            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawRoundRect(
            color = Color.LightGray,
            size = size,
            cornerRadius = CornerRadius(32f)
        )

        drawPath(
            path = path,
            color = waveColor
        )
    }
}