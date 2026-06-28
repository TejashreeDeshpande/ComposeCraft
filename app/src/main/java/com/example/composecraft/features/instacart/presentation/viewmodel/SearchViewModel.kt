package com.example.composecraft.features.instacart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composecraft.features.instacart.domain.usecase.GetCategoryProductsUseCase
import com.example.composecraft.features.instacart.domain.usecase.SearchProductsUseCase
import com.example.composecraft.features.instacart.presentation.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel(
    private val searchProducts: SearchProductsUseCase,
    private val getCategoryProducts: GetCategoryProductsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onQueryChange(query: String) {
        _state.update { it.copy(query = query, isSearching = query.isNotEmpty()) }
        if (query.isNotEmpty()) {
            _state.update { it.copy(results = searchProducts(query)) }
        }
    }

    fun selectCategory(categoryId: String) {
        _state.update {
            it.copy(
                selectedCategoryId = categoryId,
                categoryProducts = getCategoryProducts(categoryId)
            )
        }
    }

    fun clearCategory() = _state.update { it.copy(selectedCategoryId = null, categoryProducts = emptyList()) }
}
