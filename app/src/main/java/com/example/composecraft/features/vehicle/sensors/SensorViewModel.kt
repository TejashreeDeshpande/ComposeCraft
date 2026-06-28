package com.example.composecraft.features.vehicle.sensors

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SensorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SensorDashboardUiState())
    val uiState: StateFlow<SensorDashboardUiState> = _uiState.asStateFlow()

    init {
        loadSensors()
    }

    private fun loadSensors() {
        val sampleSensors = listOf(
            Sensor("1", "Front LiDAR", SensorType.LIDAR, SensorStatus.NOMINAL, 5, System.currentTimeMillis()),
            Sensor("2", "Rear Camera", SensorType.CAMERA, SensorStatus.DEGRADED, 3, System.currentTimeMillis() - 120_000),
            Sensor("3", "Side Radar", SensorType.RADAR, SensorStatus.OFFLINE, 0, System.currentTimeMillis() - 3_600_000),
            Sensor("4", "Global GPS", SensorType.GPS, SensorStatus.NOMINAL, 4, System.currentTimeMillis() - 60_000)
        )
        _uiState.update { it.copy(sensors = sampleSensors) }
    }

    fun toggleSensorExpansion(sensorId: String) {
        _uiState.update { 
            it.copy(expandedSensorId = if (it.expandedSensorId == sensorId) null else sensorId)
        }
    }
}
