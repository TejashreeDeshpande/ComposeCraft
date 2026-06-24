package com.example.composecraft.presentation.features.fittrack.screens.viewmodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

data class CreateWorkoutUiState(
    val workoutName: String = "",
    val selectedExercises: List<ExerciseUi> = emptyList(),
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)

data class ExerciseUi(
    val id: String,
    val name: String,
    val sets: Int,
    val reps: String
)

sealed interface CreateWorkoutIntent {
    data class WorkoutNameChanged(val name: String): CreateWorkoutIntent
    data class AddExercise(val exercise: ExerciseUi): CreateWorkoutIntent
    data class RemoveExercise(val exerciseId: String): CreateWorkoutIntent
    data object SaveWorkout: CreateWorkoutIntent
}

sealed interface CreateWorkoutEffect {
    data object NavigateBack: CreateWorkoutEffect
    data class ShowMessage(val message: String): CreateWorkoutEffect
}

data class WorkoutPlan(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val exercises: List<WorkoutExercise>,
    val createdAt: Long = System.currentTimeMillis()
)

data class WorkoutExercise(
    val exerciseId: String,
    val name: String,
    val sets: List<ExerciseSet>
)

data class ExerciseSet(
    val setNumber: Int,
    val targetWeight: Float?,
    val targetReps: String,
    val completed: Boolean = false
)

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val createdAt: Long
)

@Entity(tableName = "workout_exercises")
data class WorkoutExerciseEntity(
    @PrimaryKey
    val id: String,
    val workoutId: String,
    val exerciseId: String,
    val name: String
)

@Entity(tableName = "exercise_sets")
data class ExerciseSetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutExerciseId: String,
    val setNumber: Int,
    val targetWeight: Float?,
    val targetReps: Int,
    val completed: Boolean
)

data class WorkoutExerciseWithSets(
    @Embedded
    val exercise: WorkoutExerciseEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutExerciseId"
    )
    val sets: List<ExerciseSetEntity>
)

data class WorkoutWithExercises(
    @Embedded
    val workout: WorkoutEntity,

    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val exercises: List<WorkoutExerciseWithSets>
)