package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composecraft.features.instacart.data.datasource.InstacartMockDataSource
import com.example.composecraft.features.instacart.domain.usecase.GetHomeDataUseCase
import com.example.composecraft.features.instacart.domain.usecase.AddToCartUseCase
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.domain.model.Store
import com.example.composecraft.features.instacart.presentation.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val getHomeData: GetHomeDataUseCase,
    private val addToCart: AddToCartUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        load()
    }

    private fun load() {
        val (products, categories, deals) = getHomeData()
        val stores = InstacartMockDataSource.stores
        _state.update {
            it.copy(
                featuredProducts = products,
                categories = categories,
                deals = deals,
                stores = stores,
                selectedStore = stores.firstOrNull()
            )
        }
    }

    fun selectStore(store: Store) = _state.update { it.copy(selectedStore = store) }
    fun addToCart(product: Product) = addToCart.invoke(product)
}
