package com.example.composecraft.presentation.features.flightstatus.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.presentation.features.flightstatus.data.model.FlightInfoRequest
import com.example.composecraft.presentation.features.flightstatus.data.model.mockFlightDetails
import com.example.composecraft.presentation.features.flightstatus.data.repository.FlightStatusResult
import com.example.composecraft.presentation.features.flightstatus.data.usecase.GetFlightStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class FlightStatusViewModel(
    private val getFLightStatusUseCase: GetFlightStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        FlightStatusUiState(isLoading = true)
    )

    val uiState: StateFlow<FlightStatusUiState> =
        _uiState.asStateFlow()

    init {
        loadFlight(
            FlightInfoRequest(
                flightNumber = mockFlightDetails.flightNumber,
                departureDate = LocalDate.now()
            )
        )
    }

    fun loadFlight(
        request: FlightInfoRequest
    ) {
        getFLightStatusUseCase(request)
            .onStart {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                }
            }
            .onEach { result ->
                when (result) {
                    FlightStatusResult.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                        }
                    }

                    is FlightStatusResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }

                    }

                    is FlightStatusResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                flightDetails = result.data.flight,
                                currentStep = result.data.currentStep,
                                progress = result.data.progress,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
            .catch { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Unable to load flight status"
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun toggleTheme(isDark: Boolean) {
        _uiState.update { it.copy(isDarkMode = isDark) }
    }

    fun toggleSaveFlight(isSaveFlight: Boolean) {
        _uiState.update { it.copy(isSaveFlight = isSaveFlight) }
    }
}
