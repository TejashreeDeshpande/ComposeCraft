package com.example.composecraft.features.flightstatus.data.repository

import com.example.composecraft.features.flightstatus.data.model.FlightInfoRequest
import com.example.composecraft.features.flightstatus.data.model.FlightStatusResponse
import com.example.composecraft.features.flightstatus.data.model.mockFlightDetails
import com.example.composecraft.features.flightstatus.presentation.components.flightJourney
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class MockFlightRepository : FlightRepository {
    override fun observeFlightStatus(
        request: FlightInfoRequest
    ): Flow<FlightStatusResult> = flow {
        emit(FlightStatusResult.Loading)

        while (true) {
            flightJourney.forEach { step ->
                emit(
                    FlightStatusResult.Success(
                        FlightStatusResponse(
                            flight = mockFlightDetails,
                            currentStep = step,
                            progress = step.progress
                        )
                    )
                )
                delay(step.durationMillis.milliseconds)
            }
        }
    }
}