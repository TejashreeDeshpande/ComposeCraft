package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.NewsArticle
import com.example.composecraft.features.pulseinvest.domain.model.Order
import com.example.composecraft.features.pulseinvest.domain.model.OrderSide
import com.example.composecraft.features.pulseinvest.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getWatchlist(): Flow<List<Stock>>
    fun getTrending(): Flow<List<String>>
    suspend fun searchStocks(query: String): List<Stock>
    suspend fun getStockDetail(symbol: String): Result<Stock>
    suspend fun placeOrder(symbol: String, side: OrderSide, shares: Int): Result<Order>
    fun getRecentOrders(): Flow<List<Order>>
    fun getNews(): Flow<List<NewsArticle>>
}
