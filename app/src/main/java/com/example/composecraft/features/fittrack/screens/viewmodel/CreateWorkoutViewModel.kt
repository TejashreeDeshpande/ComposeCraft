package com.example.composecraft.features.fittrack.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.fittrack.screens.usecases.SaveWorkoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateWorkoutViewModel(
    private val saveWorkoutUseCase: SaveWorkoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateWorkoutUiState())
    val uiState: StateFlow<CreateWorkoutUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<CreateWorkoutEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: CreateWorkoutIntent) {
        when (intent) {
            is CreateWorkoutIntent.WorkoutNameChanged -> {
                _uiState.update {
                    it.copy(workoutName = intent.name)
                }
            }

            is CreateWorkoutIntent.AddExercise -> {
                _uiState.update {
                    it.copy(
                        selectedExercises = it.selectedExercises + intent.exercise
                    )
                }
            }

            is CreateWorkoutIntent.RemoveExercise -> {
                _uiState.update {
                    it.copy(
                        selectedExercises = it.selectedExercises
                            .filterNot { exercise -> exercise.id == intent.exerciseId }
                    )
                }
            }

            CreateWorkoutIntent.SaveWorkout -> saveWorkout()
        }
    }

    private fun saveWorkout() {
        val currentState = _uiState.value

        if (currentState.workoutName.isBlank()) {
            sendEffect(CreateWorkoutEffect.ShowMessage("Workout name is required"))
            return
        }
        if (currentState.selectedExercises.isEmpty()) {
            sendEffect(CreateWorkoutEffect.ShowMessage("Add at least one exercise"))
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            runCatching {
                saveWorkoutUseCase(
                    WorkoutPlan(
                        name = currentState.workoutName,
                        exercises = currentState.selectedExercises.map { exercise ->
                            WorkoutExercise(
                                exerciseId = exercise.id,
                                name = exercise.name,
                                sets = (1..exercise.sets).map { setNumber ->
                                    ExerciseSet(
                                        setNumber = setNumber,
                                        targetWeight = null,
                                        targetReps = exercise.reps
                                    )
                                }
                            )
                        }
                    )
                )
            }.onSuccess {
                _uiState.update {
                    it.copy(
                        isSaving = false,
                        errorMessage = null
                    )
                }

                _effect.emit(CreateWorkoutEffect.ShowMessage("Workout saved"))
                _effect.emit(CreateWorkoutEffect.NavigateBack)
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isSaving = false,
                        errorMessage = e.message
                    )
                }
                _effect.emit(
                    CreateWorkoutEffect.ShowMessage(
                        e.message ?: "Unable to save workout"
                    )
                )
            }
        }
    }

    private fun sendEffect(effect: CreateWorkoutEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}