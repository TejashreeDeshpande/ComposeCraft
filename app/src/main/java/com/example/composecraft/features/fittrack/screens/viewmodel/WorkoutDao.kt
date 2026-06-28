package com.example.composecraft.features.fittrack.screens.viewmodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction

@Dao
abstract class WorkoutDao {

    @Transaction
    open suspend fun saveWorkout(
        workout: WorkoutEntity,
        exercises: List<WorkoutExerciseEntity>,
        sets: List<ExerciseSetEntity>
    ) {
        insertWorkout(workout)
        insertExercises(exercises)
        insertSets(sets)
    }

    @Insert
    abstract suspend fun insertWorkout(workout: WorkoutEntity)

    @Insert
    abstract suspend fun insertExercises(
        exercises: List<WorkoutExerciseEntity>
    )

    @Insert
    abstract suspend fun insertSets(
        sets: List<ExerciseSetEntity>
    )
}