package com.example.composecraft.features.instacart.domain.usecase

import com.example.composecraft.features.instacart.domain.model.Address
import com.example.composecraft.features.instacart.domain.model.Cart
import com.example.composecraft.features.instacart.domain.model.DeliverySlot
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.domain.repository.OrderRepository
import com.example.composecraft.features.instacart.domain.repository.ProductRepository

class GetHomeDataUseCase(private val repo: ProductRepository) {
    operator fun invoke() = Triple(
        repo.getFeaturedProducts(),
        repo.getCategories(),
        repo.getDeals()
    )
}

class SearchProductsUseCase(private val repo: ProductRepository) {
    operator fun invoke(query: String) = repo.searchProducts(query)
}

class GetCategoryProductsUseCase(private val repo: ProductRepository) {
    operator fun invoke(categoryId: String) = repo.getProductsByCategory(categoryId)
}

class GetProductDetailUseCase(private val repo: ProductRepository) {
    operator fun invoke(productId: String) = repo.getProductById(productId)
}

class GetSimilarProductsUseCase(private val repo: ProductRepository) {
    operator fun invoke(productId: String) = repo.getSimilarProducts(productId)
}

class AddToCartUseCase(private val repo: CartRepository) {
    operator fun invoke(product: Product) = repo.addItem(product)
}

class UpdateCartQuantityUseCase(private val repo: CartRepository) {
    operator fun invoke(productId: String, quantity: Int) = repo.updateQuantity(productId, quantity)
}

class RemoveFromCartUseCase(private val repo: CartRepository) {
    operator fun invoke(productId: String) = repo.removeItem(productId)
}

class ApplyPromoCodeUseCase(private val repo: CartRepository) {
    operator fun invoke(code: String) = repo.applyPromoCode(code)
}

class PlaceOrderUseCase(
    private val orderRepo: OrderRepository,
    private val cartRepo: CartRepository
) {
    operator fun invoke(cart: Cart, address: Address, slot: DeliverySlot) =
        orderRepo.placeOrder(cart, address, slot).also { cartRepo.clearCart() }
}

class GetOrdersUseCase(private val repo: OrderRepository) {
    operator fun invoke() = repo.getOrders()
}
