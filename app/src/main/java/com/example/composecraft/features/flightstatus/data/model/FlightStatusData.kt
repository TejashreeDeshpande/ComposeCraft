package com.example.composecraft.features.flightstatus.data.model

import androidx.compose.ui.graphics.Color
import com.example.composecraft.ui.theme.FlightStatusColors

enum class FlightStatusBannerColor(
    val title: String,
    val color: Color
) {
    ON_TIME(
        "On Time",
        FlightStatusColors.OnTime
    ),
    BOARDING(
        "Boarding",
        FlightStatusColors.Boarding
    ),
    DELAYED(
        "Delayed",
        FlightStatusColors.Delayed
    ),
    CANCELLED(
        "Cancelled",
        FlightStatusColors.Cancelled
    ),
    LANDED(
        "Landed",
        FlightStatusColors.Landed
    )
}