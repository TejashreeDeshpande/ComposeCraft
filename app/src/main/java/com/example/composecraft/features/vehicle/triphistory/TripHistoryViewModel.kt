package com.example.composecraft.features.vehicle.triphistory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripHistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<TripHistoryUiState>(TripHistoryUiState.Loading)
    val uiState: StateFlow<TripHistoryUiState> = _uiState.asStateFlow()

    init {
        loadTrips()
    }

    private fun loadTrips() {
        val mockTrips = listOf(
            Trip("1", "Mon, Jun 2 · 9:14 AM", "123 Maple St", "456 Oak Ave", "$14.20", true),
            Trip("2", "Sun, Jun 1 · 6:45 PM", "Airport Terminal 1", "Home", "$32.50", false),
            Trip("3", "Fri, May 30 · 12:30 PM", "Office Plaza", "Downtown Cafe", "$8.75", true),
            Trip("4", "Wed, May 28 · 8:00 AM", "Gym Center", "Work", "$12.10", false)
        )
        _uiState.value = TripHistoryUiState.Success(mockTrips)
    }

    fun rateTrip(tripId: String) {
        val currentState = _uiState.value
        if (currentState is TripHistoryUiState.Success) {
            val updatedTrips = currentState.trips.map {
                if (it.id == tripId) it.copy(isRated = true) else it
            }
            _uiState.value = TripHistoryUiState.Success(updatedTrips)
        }
    }
}
