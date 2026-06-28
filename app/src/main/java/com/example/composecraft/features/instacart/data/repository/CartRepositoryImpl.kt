package com.example.composecraft.features.instacart.data.repository

import com.example.composecraft.features.instacart.data.datasource.InstacartMockDataSource
import com.example.composecraft.features.instacart.domain.model.Cart
import com.example.composecraft.features.instacart.domain.model.CartItem
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepositoryImpl : CartRepository {
    private val _cart = MutableStateFlow(Cart())
    override val cart: StateFlow<Cart> = _cart.asStateFlow()

    override fun addItem(product: Product) {
        val current = _cart.value
        val existing = current.items.find { it.product.id == product.id }
        val updated = if (existing != null) {
            current.items.map {
                if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            current.items + CartItem(product, 1)
        }
        _cart.value = current.copy(items = updated)
    }

    override fun removeItem(productId: String) {
        val current = _cart.value
        _cart.value = current.copy(items = current.items.filter { it.product.id != productId })
    }

    override fun updateQuantity(productId: String, quantity: Int) {
        if (quantity <= 0) { removeItem(productId); return }
        val current = _cart.value
        _cart.value = current.copy(
            items = current.items.map {
                if (it.product.id == productId) it.copy(quantity = quantity) else it
            }
        )
    }

    override fun applyPromoCode(code: String): Boolean {
        val discount = InstacartMockDataSource.promoCodes[code.uppercase()] ?: return false
        _cart.value = _cart.value.copy(promoCode = code.uppercase(), discount = discount)
        return true
    }

    override fun clearCart() {
        _cart.value = Cart()
    }
}
