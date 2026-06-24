package com.example.composecraft.presentation.features.fittrack.screens.usecases

import com.example.composecraft.presentation.features.fittrack.screens.viewmodel.WorkoutPlan

class SaveWorkoutUseCase(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(workoutPlan: WorkoutPlan) {
        repository.saveWorkout(workoutPlan)
    }
}