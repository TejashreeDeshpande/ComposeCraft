package com.example.composecraft.presentation.features.flightstatus.data.model

import com.example.composecraft.presentation.features.flightstatus.presentation.components.FlightJourneyStep

data class FlightStatusResponse(
    val flight: FlightDetails,
    val currentStep: FlightJourneyStep,
    val progress: Float
)