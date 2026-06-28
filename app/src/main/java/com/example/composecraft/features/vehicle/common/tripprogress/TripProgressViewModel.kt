package com.example.composecraft.features.vehicle.common.tripprogress

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

class TripProgressViewModel: ViewModel() {

    private val _totalDistance = MutableStateFlow(10)
    val totalDistance: StateFlow<Int> = _totalDistance

    private val _currentDistance = MutableStateFlow(0)
    private val currentDistance: StateFlow<Int> = _currentDistance

    val progress: StateFlow<Float> = combine(_currentDistance, _totalDistance,
    ) { current, total ->
        if (total > 0) current.toFloat() / total.toFloat() else 0f
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0f
    )

    private var job: Job? = null

    init {
        updateProgress()
    }
    private fun updateProgress() {
        job?.cancel()

        job = viewModelScope.launch {
            while(_currentDistance.value < _totalDistance.value) {
                delay(3000)

                _currentDistance.update { current ->
                    current + 1
                }
            }
        }
    }

    fun cancelTrip() {
        job?.cancel()
    }
}