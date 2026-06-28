package com.example.composecraft.features.vehicle.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object ColorPicker {
    fun getRandomPresetColor(): Color {
        val colorPalette = listOf(
            Color(0xFFF44336), // Red
            Color(0xFFE91E63), // Pink
            Color(0xFF9C27B0), // Purple
            Color(0xFF2196F3), // Blue
            Color(0xFF4CAF50), // Green
            Color(0xFFFFEB3B), // Yellow
            Color(0xFFFF9800)  // Orange
        )
        return colorPalette.random() // Built-in Kotlin list randomizer
    }
}

@Preview
@Composable
fun StatusBadgeWidgetPreview() {
    StateBadgeWidget()
}

@Composable
fun StateBadgeWidget() {
    val badges = listOf(
        Pair("Live", ColorPicker.getRandomPresetColor()),
        Pair("All locked", ColorPicker.getRandomPresetColor()),
        Pair("Offline", ColorPicker.getRandomPresetColor()),
        Pair("Degraded", ColorPicker.getRandomPresetColor()),
        Pair("Accessible", ColorPicker.getRandomPresetColor()),
        Pair("Off", ColorPicker.getRandomPresetColor())
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        badges.forEach { badge ->
            val (text, color) = badge
            FilterChip(
                selected = false,
                onClick = {},
                label = {
                    Text(text)
                },
                shape = RoundedCornerShape(12.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = color.copy(alpha = 0.4f),
                    labelColor = color
                ),
                border = null
            )
        }
    }

}