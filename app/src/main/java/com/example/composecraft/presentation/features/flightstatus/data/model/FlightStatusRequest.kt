package com.example.composecraft.presentation.features.flightstatus.data.model

import java.time.LocalDate

data class FlightInfoRequest(
    val flightNumber: String,
    val departureDate: LocalDate
)