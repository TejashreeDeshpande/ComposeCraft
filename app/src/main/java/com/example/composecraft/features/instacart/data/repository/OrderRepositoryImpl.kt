package com.example.composecraft.features.instacart.data.repository

import com.example.composecraft.features.instacart.data.datasource.InstacartMockDataSource
import com.example.composecraft.features.instacart.domain.model.*
import com.example.composecraft.features.instacart.domain.repository.OrderRepository
import java.util.UUID

class OrderRepositoryImpl : OrderRepository {
    private val orders = mutableListOf<Order>()

    override fun getDeliverySlots() = InstacartMockDataSource.deliverySlots
    override fun getSavedAddresses() = InstacartMockDataSource.addresses

    override fun placeOrder(cart: Cart, address: Address, slot: DeliverySlot): Order {
        val order = Order(
            id = "ORD-${UUID.randomUUID().toString().take(8).uppercase()}",
            items = cart.items,
            total = cart.total,
            address = address,
            deliverySlot = slot,
            status = OrderStatus.PLACED,
            placedAt = "Just now"
        )
        orders.add(0, order)
        return order
    }

    override fun getOrders() = orders.toList()
    override fun getOrderById(id: String) = orders.find { it.id == id }
}
