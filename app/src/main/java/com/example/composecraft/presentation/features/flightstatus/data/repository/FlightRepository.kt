package com.example.composecraft.presentation.features.flightstatus.data.repository

import com.example.composecraft.presentation.features.flightstatus.data.model.FlightInfoRequest
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun observeFlightStatus(
        request: FlightInfoRequest
    ): Flow<FlightStatusResult>
}
