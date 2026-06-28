package com.example.composecraft.features.pulseinvest.domain.model

data class Portfolio(
    val totalValue: Double,
    val todayChange: Double,
    val todayChangePercent: Double,
    val holdings: List<Holding>,
    val allocationData: List<AllocationSlice>,
    val history: List<Float> = emptyList()
) {
    val isPositiveToday: Boolean get() = todayChange >= 0
}

data class AllocationSlice(
    val label: String,
    val percent: Float,
    val value: Double,
    val colorHex: String
)
