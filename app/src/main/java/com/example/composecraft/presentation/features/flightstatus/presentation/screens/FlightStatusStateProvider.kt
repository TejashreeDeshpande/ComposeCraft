package com.example.composecraft.presentation.features.flightstatus.presentation.screens

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.composecraft.presentation.features.flightstatus.data.model.mockFlightDetails
import com.example.composecraft.presentation.features.flightstatus.data.viewmodel.FlightStatusUiState
import com.example.composecraft.presentation.features.flightstatus.presentation.components.flightJourney

class FlightStatusStateProvider : PreviewParameterProvider<FlightStatusUiState> {
    override val values = sequenceOf(
        // 1. Loading State
        FlightStatusUiState(isLoading = true),

        // 2. Success / Active Journey State
        FlightStatusUiState(
            flightDetails = mockFlightDetails,
            currentStep = flightJourney.first(),
            progress = 0.25f
        ),

        // 3. Error State
        FlightStatusUiState(
            errorMessage = "Failed to load flight status. Please try again."
        )
    )
}
