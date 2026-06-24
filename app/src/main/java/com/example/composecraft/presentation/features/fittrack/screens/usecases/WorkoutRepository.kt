package com.example.composecraft.presentation.features.fittrack.screens.usecases

import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.ExerciseSetEntity
import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.WorkoutDao
import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.WorkoutEntity
import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.WorkoutExerciseEntity
import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.WorkoutPlan
import java.util.UUID

interface WorkoutRepository {
    suspend fun saveWorkout(workoutPlan: WorkoutPlan)
}

class WorkoutRepositoryImpl(
    private val dao: WorkoutDao
): WorkoutRepository {
    override suspend fun saveWorkout(workoutPlan: WorkoutPlan) {
        val workoutEntity = WorkoutEntity(
            id = workoutPlan.id,
            name = workoutPlan.name,
            createdAt = workoutPlan.createdAt
        )

        val exerciseEntities = mutableListOf<WorkoutExerciseEntity>()
        val setEntities = mutableListOf<ExerciseSetEntity>()

        workoutPlan.exercises.forEach { exercise ->
            val exerciseId = UUID.randomUUID().toString()
            exerciseEntities.add(
                WorkoutExerciseEntity(
                    id = exerciseId,
                    workoutId = workoutPlan.id,
                    exerciseId = exercise.exerciseId,
                    name = exercise.name
                )
            )

            exercise.sets.forEach { set ->
                setEntities.add(
                    ExerciseSetEntity(
                        workoutExerciseId = exerciseId,
                        setNumber = set.setNumber,
                        targetWeight = set.targetWeight,
                        targetReps = set.targetReps.toIntOrNull() ?: 0,
                        completed = set.completed
                    )
                )
            }
        }

        dao.saveWorkout(workoutEntity, exerciseEntities, setEntities)
    }
}
