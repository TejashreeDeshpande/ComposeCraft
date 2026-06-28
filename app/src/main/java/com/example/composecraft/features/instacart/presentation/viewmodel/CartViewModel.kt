package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.domain.usecase.ApplyPromoCodeUseCase
import com.example.composecraft.features.instacart.domain.usecase.RemoveFromCartUseCase
import com.example.composecraft.features.instacart.domain.usecase.UpdateCartQuantityUseCase
import com.example.composecraft.features.instacart.presentation.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CartViewModel(
    private val cartRepo: CartRepository,
    private val updateQuantity: UpdateCartQuantityUseCase,
    private val removeItem: RemoveFromCartUseCase,
    private val applyPromo: ApplyPromoCodeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        cartRepo.cart.onEach { cart ->
            _state.update { it.copy(cart = cart) }
        }.launchIn(viewModelScope)
    }

    fun updateQuantity(productId: String, qty: Int) = updateQuantity.invoke(productId, qty)
    fun removeItem(productId: String) = removeItem.invoke(productId)
    fun onPromoInput(code: String) = _state.update { it.copy(promoInput = code, promoError = null) }

    fun applyPromo() {
        val code = _state.value.promoInput
        if (code.isBlank()) return
        val success = applyPromo(code)
        _state.update {
            it.copy(
                promoError = if (success) null else "Invalid promo code",
                promoSuccess = success
            )
        }
    }
}
