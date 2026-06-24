package com.example.composecraft.presentation.features.flightstatus.data.usecase

import com.example.composecraft.presentation.features.flightstatus.data.model.FlightInfoRequest
import com.example.composecraft.presentation.features.flightstatus.data.repository.FlightRepository
import com.example.composecraft.presentation.features.flightstatus.data.repository.FlightStatusResult
import kotlinx.coroutines.flow.Flow

class GetFlightStatusUseCase(
    private val repository: FlightRepository
) {
    operator fun invoke(
        request: FlightInfoRequest
    ): Flow<FlightStatusResult> {
        return repository.observeFlightStatus(request)
    }
}