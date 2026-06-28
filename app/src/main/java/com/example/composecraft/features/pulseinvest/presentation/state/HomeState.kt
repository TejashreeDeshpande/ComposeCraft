package com.example.composecraft.features.pulseinvest.presentation.state

import com.example.composecraft.features.pulseinvest.domain.model.NewsArticle
import com.example.composecraft.features.pulseinvest.domain.model.Stock

data class HomeState(
    val isLoading: Boolean = true,
    val userName: String = "Tejashree",
    val portfolioValue: Double = 0.0,
    val todayChange: Double = 0.0,
    val todayChangePercent: Double = 0.0,
    val chartData: List<Float> = emptyList(),
    val watchlist: List<Stock> = emptyList(),
    val trending: List<String> = emptyList(),
    val selectedTimeRange: String = "1D",
    val error: String? = null
)
