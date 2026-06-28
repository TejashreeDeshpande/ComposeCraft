package com.example.composecraft.features.fittrack.screens.usecases

import com.example.composecraft.features.fittrack.screens.viewmodel.WorkoutPlan

class SaveWorkoutUseCase(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(workoutPlan: WorkoutPlan) {
        repository.saveWorkout(workoutPlan)
    }
}