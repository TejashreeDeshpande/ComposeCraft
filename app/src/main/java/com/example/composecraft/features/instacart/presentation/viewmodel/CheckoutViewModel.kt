package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.instacart.domain.model.Address
import com.example.composecraft.features.instacart.domain.model.DeliverySlot
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.domain.repository.OrderRepository
import com.example.composecraft.features.instacart.domain.usecase.PlaceOrderUseCase
import com.example.composecraft.features.instacart.presentation.state.CheckoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val orderRepo: OrderRepository,
    private val cartRepo: CartRepository,
    private val placeOrder: PlaceOrderUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CheckoutState())
    val state = _state.asStateFlow()

    init {
        val addresses = orderRepo.getSavedAddresses()
        val slots = orderRepo.getDeliverySlots()
        _state.update {
            it.copy(
                addresses = addresses,
                deliverySlots = slots,
                selectedAddress = addresses.firstOrNull(),
                selectedSlot = slots.firstOrNull()
            )
        }
    }

    fun selectAddress(address: Address) = _state.update { it.copy(selectedAddress = address) }
    fun selectSlot(slot: DeliverySlot) = _state.update { it.copy(selectedSlot = slot) }

    fun placeOrder() {
        val address = _state.value.selectedAddress ?: return
        val slot = _state.value.selectedSlot ?: return
        val cart = cartRepo.cart.value
        _state.update { it.copy(isPlacingOrder = true) }
        viewModelScope.launch {
            val order = placeOrder(cart, address, slot)
            _state.update { it.copy(isPlacingOrder = false, placedOrder = order) }
        }
    }
}
