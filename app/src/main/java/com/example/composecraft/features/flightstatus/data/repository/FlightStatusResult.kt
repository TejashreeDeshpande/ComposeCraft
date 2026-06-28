package com.example.composecraft.features.flightstatus.data.repository

import com.example.composecraft.features.flightstatus.data.model.FlightStatusResponse


sealed interface FlightStatusResult {

    data object Loading: FlightStatusResult
    data class Success(
        val data: FlightStatusResponse
    ): FlightStatusResult

    data class Error(
        val message: String
    ): FlightStatusResult
}
