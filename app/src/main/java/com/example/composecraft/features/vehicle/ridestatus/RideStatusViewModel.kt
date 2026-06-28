package com.example.composecraft.features.vehicle.ridestatus

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RideStatusViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RideUiState())
    val uiState: StateFlow<RideUiState> = _uiState.asStateFlow()

    fun updateStatus(status: RideStatus) {
        _uiState.update { it.copy(status = status) }
    }

    fun cancelRide() {
        // Handle cancel logic
    }
}
