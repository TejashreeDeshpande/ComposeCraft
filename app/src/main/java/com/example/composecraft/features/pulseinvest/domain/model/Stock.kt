package com.example.composecraft.features.pulseinvest.domain.model

data class Stock(
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val marketCap: String = "",
    val peRatio: String = "",
    val high52w: Double = 0.0,
    val low52w: Double = 0.0,
    val about: String = "",
    val sparkline: List<Float> = emptyList()
) {
    val isPositive: Boolean get() = change >= 0
}
