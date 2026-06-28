package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composecraft.features.instacart.domain.usecase.GetOrdersUseCase
import com.example.composecraft.features.instacart.presentation.state.OrdersState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrdersViewModel(private val getOrders: GetOrdersUseCase) : ViewModel() {
    private val _state = MutableStateFlow(OrdersState())
    val state = _state.asStateFlow()

    fun refresh() = _state.update { it.copy(orders = getOrders()) }
}
