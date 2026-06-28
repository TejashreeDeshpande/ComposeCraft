package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.model.OrderSide
import com.example.composecraft.features.pulseinvest.domain.model.Stock
import com.example.composecraft.features.pulseinvest.domain.usecase.*
import com.example.composecraft.features.pulseinvest.presentation.state.OrderState
import com.example.composecraft.features.pulseinvest.presentation.state.StockDetailState
import com.example.composecraft.features.pulseinvest.presentation.state.UiEffect
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class StockEffect : UiEffect {
    data class ShowError(val message: String) : StockEffect()
    data object NavigateToPortfolio : StockEffect()
}

class StockViewModel(
    private val getStockDetailUseCase: GetStockDetailUseCase,
    private val searchStocksUseCase: SearchStocksUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val getAvailableBalanceUseCase: GetAvailableBalanceUseCase
) : ViewModel() {

    private val _effect = Channel<StockEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val _detailState = MutableStateFlow(StockDetailState())
    val detailState = _detailState.asStateFlow()

    private val _orderState = MutableStateFlow(OrderState())
    val orderState = _orderState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Stock>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    init {
        viewModelScope.launch {
            @OptIn(FlowPreview::class)
            searchQuery.debounce(300).collect { query ->
                if (query.isBlank()) { _searchResults.value = emptyList(); return@collect }
                _searchResults.value = searchStocksUseCase(query)
            }
        }
    }

    fun loadStock(symbol: String) = viewModelScope.launch {
        _detailState.update { it.copy(isLoading = true) }
        getStockDetailUseCase(symbol)
            .onSuccess { stock ->
                _detailState.update { it.copy(isLoading = false, stock = stock) }
                _orderState.update  { it.copy(stock = stock) }
            }
            .onFailure { e -> _detailState.update { it.copy(isLoading = false, error = e.message) } }
    }

    fun onSearchQueryChange(q: String) { _searchQuery.value = q }
    fun onSideChange(side: OrderSide)  = _orderState.update { it.copy(side = side) }
    fun incrementShares()              = _orderState.update { it.copy(shares = it.shares + 1) }
    fun decrementShares()              = _orderState.update { if (it.shares > 1) it.copy(shares = it.shares - 1) else it }
    fun onTimeRangeSelected(r: String) = _detailState.update { it.copy(selectedTimeRange = r) }

    fun placeOrder() = viewModelScope.launch {
        val s   = _orderState.value
        val sym = s.stock?.symbol ?: return@launch
        _orderState.update { it.copy(isLoading = true) }
        placeOrderUseCase(sym, s.side, s.shares)
            .onSuccess { order ->
                _orderState.update { it.copy(isLoading = false, placedOrder = order) }
                // Example of sending an effect
            }
            .onFailure { e ->
                _orderState.update { it.copy(isLoading = false, error = e.message) }
                _effect.send(StockEffect.ShowError(e.message ?: "Unknown Error"))
            }
    }

    fun clearPlacedOrder() = _orderState.update { it.copy(placedOrder = null) }
}
