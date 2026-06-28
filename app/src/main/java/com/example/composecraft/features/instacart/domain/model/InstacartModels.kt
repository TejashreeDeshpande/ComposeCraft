package com.example.composecraft.features.instacart.domain.model

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val price: Double,
    val originalPrice: Double?,
    val unit: String,
    val imageEmoji: String,
    val categoryId: String,
    val rating: Float,
    val reviewCount: Int,
    val inStock: Boolean,
    val isOrganic: Boolean,
    val tags: List<String> = emptyList()
)

data class Category(
    val id: String,
    val name: String,
    val emoji: String,
    val color: Long
)

data class CartItem(
    val product: Product,
    val quantity: Int
)

data class Cart(
    val items: List<CartItem> = emptyList(),
    val promoCode: String? = null,
    val discount: Double = 0.0
) {
    val subtotal: Double get() = items.sumOf { it.product.price * it.quantity }
    val deliveryFee: Double get() = if (subtotal >= 35.0) 0.0 else 3.99
    val serviceFee: Double get() = subtotal * 0.05
    val total: Double get() = subtotal + deliveryFee + serviceFee - discount
    val itemCount: Int get() = items.sumOf { it.quantity }
}

data class Address(
    val id: String,
    val label: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)

data class DeliverySlot(
    val id: String,
    val label: String,
    val timeRange: String,
    val price: Double
)

data class Order(
    val id: String,
    val items: List<CartItem>,
    val total: Double,
    val address: Address,
    val deliverySlot: DeliverySlot,
    val status: OrderStatus,
    val placedAt: String
)

enum class OrderStatus(val label: String, val emoji: String) {
    PLACED("Order Placed", "📋"),
    BEING_SHOPPED("Being Shopped", "🛒"),
    OUT_FOR_DELIVERY("Out for Delivery", "🚗"),
    DELIVERED("Delivered", "✅")
}

data class Store(
    val id: String,
    val name: String,
    val emoji: String,
    val deliveryTime: String,
    val minOrder: Double
)

data class Deal(
    val product: Product,
    val discountPercent: Int,
    val expiresIn: String
)
