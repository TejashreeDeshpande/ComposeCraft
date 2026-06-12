package com.example.composecraft.presentation.features.vehicle.common.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProgressViewModel : ViewModel() {
    private val _currentDistance = MutableStateFlow(0.0f)
    val currentDistance: StateFlow<Float> = _currentDistance

    private val _targetDistance = MutableStateFlow(0.0f)
    val targetDistance: StateFlow<Float> = _targetDistance

    val progress = combine(_currentDistance, _targetDistance) { current, target ->
        if (target > 0f) (current / target).coerceIn(0f, 1f) else 0f
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0f
    )

    private var job: Job? = null

    fun startTrip(distance: Float) {
        job?.cancel()
        _currentDistance.value = 0.0f
        _targetDistance.value = distance

        job = viewModelScope.launch {
            while (_currentDistance.value < distance) {
                delay(1000)
                _currentDistance.update {
                    (it + 1f).coerceAtMost(distance)
                }
            }
        }
    }

    fun stop() {
        job?.cancel()
    }
}