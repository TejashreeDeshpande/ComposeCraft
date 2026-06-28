package com.example.composecraft.data.fittrack

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composecraft.features.fittrack.screens.viewmodel.WorkoutDao
import com.example.composecraft.features.fittrack.screens.viewmodel.WorkoutEntity
import com.example.composecraft.features.fittrack.screens.viewmodel.WorkoutExerciseEntity
import com.example.composecraft.features.fittrack.screens.viewmodel.ExerciseSetEntity

@Database(
    entities = [
        WorkoutEntity::class,
        WorkoutExerciseEntity::class,
        ExerciseSetEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}
