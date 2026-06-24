package com.example.composecraft.presentation.features.flightstatus.data.viewmodel

import com.example.composecraft.presentation.features.flightstatus.data.model.FlightDetails
import com.example.composecraft.presentation.features.flightstatus.presentation.components.FlightJourneyStep
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class FlightStatusUiState(
    val date: String = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date()),
    val isLoading: Boolean = false,
    val flightDetails: FlightDetails? = null,
    val currentStep: FlightJourneyStep? = null,
    val progress: Float = 0f,
    val errorMessage: String? = null,

    val isRefreshing: Boolean = false,
    val showBottomSheet: Boolean = false,
    val snackbarMessage: String? = null,
    val isDarkMode: Boolean = false,
    val isSaveFlight: Boolean = false
)