package com.example.composecraft.data.fittrack

import androidx.compose.ui.graphics.Color

data class Workout(
    val name: String,
    val details: String,
    val status: WorkoutStatus,
)

enum class WorkoutStatus(
    val type: String,
    val iconStr: String,
    val backgroundColor: Color
) {
    STOPPED("Stopped", "▶", Color.Red),
    COMPLETED("Completed", "✓", Color.Yellow),
    IN_PROGRESS("In Progress", "⏸️", Color.Gray)
}