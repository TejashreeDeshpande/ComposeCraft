package com.example.composecraft.features.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewMagneticChipsDemo() {
    MagneticChipsDemo()
}
@Composable
fun MagneticChipsDemo() {
    val chips = listOf("AI", "Motion", "Compose", "Canvas", "UX")
    var pointer by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        pointer = event.changes.first().position
                    }
                }
            }
            .padding(32.dp)
    ) {
        chips.forEachIndexed { index, chip ->
            MagneticChip(
                text = chip,
                baseOffset = Offset(
                    x = 40f,
                    y = 120f + index * 90f
                ),
                pointer = pointer
            )
        }
    }
}

@Composable
private fun MagneticChip(
    text: String,
    baseOffset: Offset,
    pointer: Offset
) {
    val distance = (pointer - baseOffset).getDistance()
    val attraction = if (distance < 250f) 40f else 0f

    val offsetX by animateFloatAsState(
        targetValue = if (distance < 250f) attraction else 0f,
        label = "chipX"
    )

    AssistChip(
        modifier = Modifier.offset {
            IntOffset(
                baseOffset.x.toInt() + offsetX.toInt(),
                baseOffset.y.toInt()
            )
        },
        onClick = {},
        label = { Text(text) }
    )
}