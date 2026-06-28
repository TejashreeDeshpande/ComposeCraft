package com.example.composecraft.features.pulseinvest.presentation.state

import com.example.composecraft.features.pulseinvest.domain.model.AllocationSlice
import com.example.composecraft.features.pulseinvest.domain.model.Holding
import com.example.composecraft.features.pulseinvest.domain.model.Stock

data class PortfolioState(
    val isLoading: Boolean = true,
    val totalValue: Double = 0.0,
    val todayChange: Double = 0.0,
    val todayChangePercent: Double = 0.0,
    val holdings: List<Holding> = emptyList(),
    val watchlist: List<Stock> = emptyList(),
    val allocationData: List<AllocationSlice> = emptyList(),
    val selectedTab: String = "Holdings",
    val error: String? = null
)
