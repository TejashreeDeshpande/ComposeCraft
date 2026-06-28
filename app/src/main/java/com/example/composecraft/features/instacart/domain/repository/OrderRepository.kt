package com.example.composecraft.features.instacart.domain.repository

import com.example.composecraft.features.instacart.domain.model.Address
import com.example.composecraft.features.instacart.domain.model.Cart
import com.example.composecraft.features.instacart.domain.model.DeliverySlot
import com.example.composecraft.features.instacart.domain.model.Order

interface OrderRepository {
    fun getDeliverySlots(): List<DeliverySlot>
    fun getSavedAddresses(): List<Address>
    fun placeOrder(cart: Cart, address: Address, slot: DeliverySlot): Order
    fun getOrders(): List<Order>
    fun getOrderById(id: String): Order?
}
