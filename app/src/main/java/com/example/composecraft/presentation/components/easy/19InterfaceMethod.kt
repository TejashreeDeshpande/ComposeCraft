package com.example.composecraft.presentation.components.easy

import kotlin.math.*

//Interface default method
//Define a Locatable interface with lat/lon properties
// and a default distanceTo(other: Locatable): Double using the Haversine approximation.
interface Locatable {
    val latitude: Double
    val longitude: Double

    fun distanceTo(other: Locatable): Double {
        val earthRadiusKm = 6371.0

        // Convert degrees to radians
        val dLat = Math.toRadians(other.latitude - this.latitude)
        val dLon = Math.toRadians(other.longitude - this.longitude)

        val lat1Rad = Math.toRadians(this.latitude)
        val lat2Rad = Math.toRadians(other.latitude)

        // Haversine formula
        val a = sin(dLat / 2).pow(2) +
                cos(lat1Rad) * cos(lat2Rad) * sin(dLon / 2).pow(2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadiusKm * c
    }
}