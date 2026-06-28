package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.usecase.GetPortfolioUseCase
import com.example.composecraft.features.pulseinvest.domain.usecase.GetWatchlistUseCase
import com.example.composecraft.features.pulseinvest.presentation.state.PortfolioState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PortfolioViewModel(
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val getWatchlistUseCase: GetWatchlistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PortfolioState())
    val state = _state.asStateFlow()

    init { load() }

    private fun load() = viewModelScope.launch {
        combine(getPortfolioUseCase(), getWatchlistUseCase()) { portfolio, watchlist ->
            PortfolioState(
                isLoading          = false,
                totalValue         = portfolio.totalValue,
                todayChange        = portfolio.todayChange,
                todayChangePercent = portfolio.todayChangePercent,
                holdings           = portfolio.holdings,
                watchlist          = watchlist,
                allocationData     = portfolio.allocationData
            )
        }.catch { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
         .collect  { _state.value = it }
    }

    fun onTabSelected(tab: String) = _state.update { it.copy(selectedTab = tab) }
}
