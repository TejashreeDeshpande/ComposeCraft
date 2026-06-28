package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.domain.usecase.AddToCartUseCase
import com.example.composecraft.features.instacart.domain.usecase.GetProductDetailUseCase
import com.example.composecraft.features.instacart.domain.usecase.GetSimilarProductsUseCase
import com.example.composecraft.features.instacart.presentation.state.ProductDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel(
    productId: String,
    getProductDetail: GetProductDetailUseCase,
    getSimilarProducts: GetSimilarProductsUseCase,
    private val addToCart: AddToCartUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProductDetailState())
    val state = _state.asStateFlow()

    init {
        val product = getProductDetail(productId)
        val similar = getSimilarProducts(productId)
        _state.update { it.copy(product = product, similarProducts = similar) }
    }

    fun addToCart() {
        val product = _state.value.product ?: return
        addToCart(product)
        _state.update { it.copy(quantityInCart = it.quantityInCart + 1) }
    }
}
