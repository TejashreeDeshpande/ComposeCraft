package com.example.composecraft.features.instacart.presentation.state

import com.example.composecraft.features.instacart.domain.model.*

data class HomeState(
    val featuredProducts: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val deals: List<Deal> = emptyList(),
    val stores: List<Store> = emptyList(),
    val selectedStore: Store? = null,
    val isLoading: Boolean = false
)

data class SearchState(
    val query: String = "",
    val results: List<Product> = emptyList(),
    val selectedCategoryId: String? = null,
    val categoryProducts: List<Product> = emptyList(),
    val isSearching: Boolean = false
)

data class ProductDetailState(
    val product: Product? = null,
    val similarProducts: List<Product> = emptyList(),
    val quantityInCart: Int = 0,
    val isLoading: Boolean = false
)

data class CartState(
    val cart: Cart = Cart(),
    val promoInput: String = "",
    val promoError: String? = null,
    val promoSuccess: Boolean = false
)

data class CheckoutState(
    val addresses: List<Address> = emptyList(),
    val deliverySlots: List<DeliverySlot> = emptyList(),
    val selectedAddress: Address? = null,
    val selectedSlot: DeliverySlot? = null,
    val isPlacingOrder: Boolean = false,
    val placedOrder: Order? = null
)

data class OrdersState(
    val orders: List<Order> = emptyList()
)
