package com.example.composecraft.features.pulseinvest.domain.model

enum class CourseStatus { LOCKED, IN_PROGRESS, COMPLETED }

data class Course(
    val id: String,
    val title: String,
    val durationMinutes: Int,
    val status: CourseStatus,
    val emoji: String = "📚"
)

data class LearningProgress(
    val level: Int,
    val levelTitle: String,
    val currentXp: Int,
    val maxXp: Int,
    val courses: List<Course>
)
