package com.example.composecraft.features.instacart.domain.repository

import com.example.composecraft.features.instacart.domain.model.Category
import com.example.composecraft.features.instacart.domain.model.Deal
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.domain.model.Store

interface ProductRepository {
    fun getCategories(): List<Category>
    fun getFeaturedProducts(): List<Product>
    fun getProductsByCategory(categoryId: String): List<Product>
    fun searchProducts(query: String): List<Product>
    fun getProductById(id: String): Product?
    fun getDeals(): List<Deal>
    fun getStores(): List<Store>
    fun getSimilarProducts(productId: String): List<Product>
}
