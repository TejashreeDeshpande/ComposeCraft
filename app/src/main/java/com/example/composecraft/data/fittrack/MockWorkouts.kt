package com.example.composecraft.data.fittrack

object MockWorkouts {
    val list = listOf<Workout>(
        Workout(
            name = "Push Day",
            details = "3 exercises * 2 days ago",
            status = WorkoutStatus.STOPPED,
        ),
        Workout(
            name = "Pull Day",
            details = "3 exercises * Yesterday",
            status = WorkoutStatus.COMPLETED,
        ),
        Workout(
            name = "Leg Day",
            details = "4 exercises * 4 days ago",
            status = WorkoutStatus.IN_PROGRESS,
        )
    )
}