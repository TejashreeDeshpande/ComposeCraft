package com.example.composecraft.features.pulseinvest.presentation.state

import com.example.composecraft.features.pulseinvest.domain.model.Order
import com.example.composecraft.features.pulseinvest.domain.model.OrderSide
import com.example.composecraft.features.pulseinvest.domain.model.Stock

data class StockDetailState(
    val isLoading: Boolean = true,
    val stock: Stock? = null,
    val selectedTimeRange: String = "1M",
    val error: String? = null
)

data class OrderState(
    val stock: Stock? = null,
    val side: OrderSide = OrderSide.BUY,
    val shares: Int = 1,
    val availableBalance: Double = 10150.0,
    val isLoading: Boolean = false,
    val placedOrder: Order? = null,
    val error: String? = null
) {
    val estimatedCost: Double get() = (stock?.price ?: 0.0) * shares
}
