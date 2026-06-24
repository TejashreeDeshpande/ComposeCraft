package com.example.composecraft.presentation.features.flightstatus.data.model

import com.example.composecraft.presentation.features.flightstatus.presentation.components.FlightJourneyStep
import com.example.composecraft.ui.theme.FlightStatus

data class FlightDetails(
    val id: String,
    val airlineName: String,
    val flightNumber: String,
    val aircraft: Aircraft,
    val origin: Airport,
    val destination: Airport,
    val departureGate: String,
    val arrivalGate: String,
    val scheduledDepartureTimeMillis: Long,
    val scheduledArrivalTimeMillis: Long,
    val estimatedArrivalTimeMillis: Long?,
    val status: FlightStatus,
    val journeyStep: FlightJourneyStep,
    val progress: Float
) {
    val route = "$flightNumber • ${origin.city} -> ${destination.city}"
}

data class Aircraft(
    val model: String,
    val registration: String
)

data class Airport(
    val code: String,
    val city: String,
    val name: String,
)