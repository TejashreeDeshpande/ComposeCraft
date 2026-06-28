package com.example.composecraft.features.flightstatus.data.model

import com.example.composecraft.features.flightstatus.presentation.components.flightJourney


val initialStep = flightJourney.first()

val mockFlightDetails = FlightDetails(
    id = "AF0083",
    airlineName = "AIR FRANCE",
    flightNumber = "AF0083",
    aircraft = Aircraft(
        model = "Boeing 777-300ER",
        registration = "F-GSQM"
    ),
    origin = Airport(
        code = "SFO",
        city = "San Francisco",
        name = "San Francisco International Airport"
    ),
    destination = Airport(
        code = "CDG",
        city = "Paris",
        name = "Charles de Gaulle Airport"
    ),
    departureGate = "A11",
    arrivalGate = "M26",
    scheduledDepartureTimeMillis = System.currentTimeMillis(),
    scheduledArrivalTimeMillis = System.currentTimeMillis() + (10 * 60 * 60 * 1000),
    estimatedArrivalTimeMillis = System.currentTimeMillis() + (10 * 60 * 60 * 1000),
    status = initialStep.status,
    journeyStep = initialStep,
    progress = initialStep.progress
)
