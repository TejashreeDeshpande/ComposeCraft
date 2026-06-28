package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.usecase.GetCurrentUserUseCase
import com.example.composecraft.features.pulseinvest.domain.usecase.GetPortfolioUseCase
import com.example.composecraft.features.pulseinvest.domain.usecase.GetTrendingUseCase
import com.example.composecraft.features.pulseinvest.domain.usecase.GetWatchlistUseCase
import com.example.composecraft.features.pulseinvest.presentation.state.HomeState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init { load() }

    private fun load() = viewModelScope.launch {
        combine(
            getPortfolioUseCase(),
            getWatchlistUseCase(),
            getTrendingUseCase(),
            getCurrentUserUseCase()
        ) { portfolio, watchlist, trending, user ->
            HomeState(
                isLoading          = false,
                userName           = user?.name ?: "User",
                portfolioValue     = portfolio.totalValue,
                todayChange        = portfolio.todayChange,
                todayChangePercent = portfolio.todayChangePercent,
                chartData          = portfolio.history,
                watchlist          = watchlist,
                trending           = trending
            )
        }.catch { e ->
            _state.update { it.copy(isLoading = false, error = e.message) }
        }.collect { newState ->
            _state.value = newState
        }
    }

    fun onTimeRangeSelected(range: String) = _state.update { it.copy(selectedTimeRange = range) }
}
