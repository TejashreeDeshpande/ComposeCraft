package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.*
import com.example.composecraft.features.pulseinvest.domain.repository.StockRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : StockRepository {

    override fun getWatchlist(): Flow<List<Stock>> = flow {
        emit(dataSource.getStocks())
    }

    override fun getTrending(): Flow<List<String>> = flow {
        emit(dataSource.getTrending())
    }

    override suspend fun searchStocks(query: String): List<Stock> {
        delay(300)
        return dataSource.getStocks().filter {
            it.symbol.contains(query, ignoreCase = true) ||
            it.name.contains(query, ignoreCase = true)
        }
    }

    override suspend fun getStockDetail(symbol: String): Result<Stock> {
        delay(200)
        val stock = dataSource.getStocks().find { it.symbol == symbol }
        return if (stock != null) Result.success(stock)
        else Result.failure(Exception("Stock not found: $symbol"))
    }

    override suspend fun placeOrder(symbol: String, side: OrderSide, shares: Int): Result<Order> {
        delay(1000)
        val stock = dataSource.getStocks().find { it.symbol == symbol }
            ?: return Result.failure(Exception("Stock not found"))
        val order = Order(
            id = "ORD-${UUID.randomUUID().toString().take(8).uppercase()}",
            stock = stock,
            side = side,
            type = OrderType.MARKET,
            shares = shares,
            estimatedPrice = stock.price,
            status = OrderStatus.EXECUTED,
            createdAt = LocalDateTime.now()
        )
        return Result.success(order)
    }

    override fun getRecentOrders(): Flow<List<Order>> = flow {
        emit(emptyList())
    }

    override fun getNews(): Flow<List<NewsArticle>> = flow {
        emit(dataSource.getNews())
    }
}
