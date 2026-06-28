package com.example.composecraft.features.pulseinvest.domain.model

data class Holding(
    val stock: Stock,
    val shares: Int,
    val avgBuyPrice: Double
) {
    val totalValue: Double get() = stock.price * shares
    val totalGain: Double get() = (stock.price - avgBuyPrice) * shares
    val gainPercent: Double get() = ((stock.price - avgBuyPrice) / avgBuyPrice) * 100
    val isPositive: Boolean get() = totalGain >= 0
}
