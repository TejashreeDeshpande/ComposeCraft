package com.example.composecraft.features.instacart.data.repository

import com.example.composecraft.features.instacart.data.datasource.InstacartMockDataSource
import com.example.composecraft.features.instacart.domain.model.*
import com.example.composecraft.features.instacart.domain.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {
    private val ds = InstacartMockDataSource

    override fun getCategories() = ds.categories
    override fun getFeaturedProducts() = ds.products.filter { it.inStock }.take(12)
    override fun getProductsByCategory(categoryId: String) =
        ds.products.filter { it.categoryId == categoryId }
    override fun searchProducts(query: String) =
        ds.products.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.brand.contains(query, ignoreCase = true) ||
            it.tags.any { t -> t.contains(query, ignoreCase = true) }
        }
    override fun getProductById(id: String) = ds.products.find { it.id == id }
    override fun getDeals() = ds.getDeals()
    override fun getStores() = ds.stores
    override fun getSimilarProducts(productId: String): List<Product> {
        val product = ds.products.find { it.id == productId } ?: return emptyList()
        return ds.products.filter { it.categoryId == product.categoryId && it.id != productId }.take(6)
    }
}
