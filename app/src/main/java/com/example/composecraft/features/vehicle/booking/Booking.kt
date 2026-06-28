package com.example.composecraft.features.vehicle.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

// --- Data / State ---

data class BookingDetails(
    val pickupAddress: String,
    val dropoffAddress: String,
    val estimatedFare: String,    // e.g. "$12.40"
    val etaMinutes: Int
)

sealed interface BookingConfirmationState {
    data class Cancellable(val secondsRemaining: Int) : BookingConfirmationState
    object Confirmed : BookingConfirmationState
    object Cancelled : BookingConfirmationState
}

// --- ViewModel (sketch, not compiled) ---

class BookingConfirmationViewModel : ViewModel() {

    private val CANCEL_WINDOW_SECONDS = 10

    private val _state = MutableStateFlow<BookingConfirmationState>(
        BookingConfirmationState.Cancellable(CANCEL_WINDOW_SECONDS)
    )
    val state: StateFlow<BookingConfirmationState> = _state.asStateFlow()

    init {
        startCountdown()
    }

    private fun startCountdown() {
        viewModelScope.launch {
            for (seconds in CANCEL_WINDOW_SECONDS downTo 1) {
                _state.value = BookingConfirmationState.Cancellable(seconds)
                delay(1000L.milliseconds)
            }
            // Only confirm if user hasn't canceled
            if (_state.value !is BookingConfirmationState.Cancelled) {
                _state.value = BookingConfirmationState.Confirmed
            }
        }
    }

    private fun onCancelBooking() {
        if (_state.value is BookingConfirmationState.Cancellable) {
            _state.value = BookingConfirmationState.Cancelled
        }
    }
}