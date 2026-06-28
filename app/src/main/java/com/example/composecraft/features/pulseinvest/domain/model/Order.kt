package com.example.composecraft.features.pulseinvest.domain.model

import java.time.LocalDateTime

enum class OrderType { MARKET, LIMIT }
enum class OrderSide { BUY, SELL }
enum class OrderStatus { PENDING, EXECUTED, CANCELLED }

data class Order(
    val id: String,
    val stock: Stock,
    val side: OrderSide,
    val type: OrderType,
    val shares: Int,
    val estimatedPrice: Double,
    val fees: Double = 0.0,
    val status: OrderStatus = OrderStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    val total: Double get() = estimatedPrice * shares + fees
}
