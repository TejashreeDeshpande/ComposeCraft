package com.example.composecraft.features.flightstatus.data.repository

import com.example.composecraft.features.flightstatus.data.model.FlightInfoRequest
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun observeFlightStatus(
        request: FlightInfoRequest
    ): Flow<FlightStatusResult>
}
