package com.example.composecraft.presentation.components.easy

//List transformations
//Given a list of coordinate pairs,
//use map + filter to return only pairs where both values are within ±90 lat and ±180 lon.
val mockCoordinates = listOf(
    // Valid Coordinates (Standard & Boundary)
    Pair(37.5485, -121.9886), // Fremont, CA
    Pair(90.0, 0.0),          // North Pole
    Pair(-90.0, 0.0),         // South Pole
    Pair(0.0, 180.0),         // International Date Line (West)
    Pair(0.0, -180.0),        // International Date Line (East)

    // Invalid Coordinates (Out of Range)
    Pair(90.1, 0.0),          // Invalid Latitude (too high)
    Pair(-90.1, 0.0),         // Invalid Latitude (too low)
    Pair(0.0, 180.1),         // Invalid Longitude (too high)
    Pair(0.0, -180.1),        // Invalid Longitude (too low)

    // Null/Default (Often used as empty/error states)
    Pair(0.0, 0.0)            // Null Island (Typically invalid if used for real tracking)
)

fun listTransaformations(): List<Pair<Double, Double>> {
    return mockCoordinates.filter { (lat, lng) ->
        lat in -90.0..90.0 && lng in -180.0..180.0
    }
}