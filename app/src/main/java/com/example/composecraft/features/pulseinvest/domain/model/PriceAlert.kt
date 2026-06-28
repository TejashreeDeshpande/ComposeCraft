package com.example.composecraft.features.pulseinvest.domain.model

data class PriceAlert(
    val id: String,
    val symbol: String,
    val emoji: String,
    val condition: String,
    val isEnabled: Boolean
)
