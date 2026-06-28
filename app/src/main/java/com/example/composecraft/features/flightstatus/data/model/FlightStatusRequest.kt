package com.example.composecraft.features.flightstatus.data.model

import java.time.LocalDate

data class FlightInfoRequest(
    val flightNumber: String,
    val departureDate: LocalDate
)