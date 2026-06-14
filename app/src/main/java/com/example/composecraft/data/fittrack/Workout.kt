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
    val iconTint: Color,
    val backgroundColor: Color
) {
    STOPPED(
        type = "Stopped",
        iconStr = "▶",
        iconTint = Color.White,
        backgroundColor = Color(0xFFFF7F50)
    ),

    COMPLETED(
        type = "Completed",
        iconStr = "✔",
        iconTint = Color.DarkGray,
        backgroundColor = Color(0xFFFFFED0)
    ),

    IN_PROGRESS(
        type = "In Progress",
        iconStr = "■",
        iconTint = Color.DarkGray,
        backgroundColor = Color(0xFFD3D3D3)
    )
}