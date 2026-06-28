package com.example.composecraft.features.instacart.domain.repository

import com.example.composecraft.features.instacart.domain.model.Cart
import com.example.composecraft.features.instacart.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cart: StateFlow<Cart>
    fun addItem(product: Product)
    fun removeItem(productId: String)
    fun updateQuantity(productId: String, quantity: Int)
    fun applyPromoCode(code: String): Boolean
    fun clearCart()
}
