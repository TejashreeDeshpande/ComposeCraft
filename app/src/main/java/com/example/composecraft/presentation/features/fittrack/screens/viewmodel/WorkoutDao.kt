package com.example.composecraft.presentation.features.fittrack.screens.viewmodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction

@Dao
interface WorkoutDao {

    @Transaction
    suspend fun saveWorkout(
        workout: WorkoutEntity,
        exercises: List<WorkoutExerciseEntity>,
        sets: List<ExerciseSetEntity>
    ) {
        insertWorkout(workout)
        insertExercises(exercises)
        insertSets(sets)
    }

    @Insert
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Insert
    suspend fun insertExercises(
        exercises: List<WorkoutExerciseEntity>
    )

    @Insert
    suspend fun insertSets(
        sets: List<ExerciseSetEntity>
    )
}