package com.example.composecraft.features.vehicle.safetycontrols

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// --- Data / State ---

enum class SafetyActionType { PULL_OVER, EMERGENCY_STOP, CONTACT_SUPPORT }

enum class PullOverOption { IMMEDIATE, NEXT_INTERSECTION }

sealed interface SafetyUiState {
    object Idle : SafetyUiState
    object ConfirmPullOver : SafetyUiState
    object ConfirmEmergencyStop : SafetyUiState

    // Active states shown as in-ride banners
    object PullingOver : SafetyUiState
    object EmergencyStopped : SafetyUiState
    object SupportConnected : SafetyUiState
}

data class SafetyPanelUiState(
    val safetyState: SafetyUiState = SafetyUiState.Idle,
    val holdProgress: Float = 0f,      // 0.0 – 1.0 for SOS hold gesture
    val operatorCallDuration: String = "00:00"
)

// --- ViewModel ---

class SafetyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SafetyPanelUiState())
    val uiState: StateFlow<SafetyPanelUiState> = _uiState.asStateFlow()

    private var holdJob: Job? = null

    fun onSafetyAction(action: SafetyActionType) {
        _uiState.update { current ->
            when (action) {
                SafetyActionType.PULL_OVER      -> current.copy(safetyState = SafetyUiState.ConfirmPullOver)
                SafetyActionType.EMERGENCY_STOP -> current.copy(safetyState = SafetyUiState.ConfirmEmergencyStop)
                SafetyActionType.CONTACT_SUPPORT -> current // handled via deep link / call intent
            }
        }
    }

    fun onConfirmPullOver(option: PullOverOption) {
        _uiState.update { it.copy(safetyState = SafetyUiState.PullingOver) }
        // In real implementation: dispatch command to vehicle API
    }

    fun onSosHoldStart() {
        holdJob = viewModelScope.launch {
            val durationMs = 3000L
            val tickMs = 50L
            var elapsed = 0L
            while (elapsed < durationMs) {
                delay(tickMs)
                elapsed += tickMs
                _uiState.update { it.copy(holdProgress = elapsed / durationMs.toFloat()) }
            }
            triggerEmergencyStop()
        }
    }

    fun onSosHoldEnd() {
        holdJob?.cancel()
        _uiState.update { it.copy(holdProgress = 0f) }
    }

    private fun triggerEmergencyStop() {
        _uiState.update { it.copy(safetyState = SafetyUiState.EmergencyStopped, holdProgress = 0f) }
        // Dispatch emergency stop to vehicle + connect operator
    }

    fun onDismiss() {
        _uiState.update { it.copy(safetyState = SafetyUiState.Idle, holdProgress = 0f) }
    }
}