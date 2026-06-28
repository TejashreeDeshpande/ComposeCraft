package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.model.NewsArticle
import com.example.composecraft.features.pulseinvest.domain.model.Order
import com.example.composecraft.features.pulseinvest.domain.model.OrderSide
import com.example.composecraft.features.pulseinvest.domain.model.Stock
import com.example.composecraft.features.pulseinvest.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchlistUseCase @Inject constructor(private val repo: StockRepository) {
    operator fun invoke(): Flow<List<Stock>> = repo.getWatchlist()
}

class SearchStocksUseCase @Inject constructor(private val repo: StockRepository) {
    suspend operator fun invoke(query: String): List<Stock> = repo.searchStocks(query)
}

class GetStockDetailUseCase @Inject constructor(private val repo: StockRepository) {
    suspend operator fun invoke(symbol: String): Result<Stock> = repo.getStockDetail(symbol)
}

class PlaceOrderUseCase @Inject constructor(private val repo: StockRepository) {
    suspend operator fun invoke(symbol: String, side: OrderSide, shares: Int): Result<Order> =
        repo.placeOrder(symbol, side, shares)
}

class GetNewsUseCase @Inject constructor(private val repo: StockRepository) {
    operator fun invoke(): Flow<List<NewsArticle>> = repo.getNews()
}

class GetTrendingUseCase @Inject constructor(private val repo: StockRepository) {
    operator fun invoke(): Flow<List<String>> = repo.getTrending()
}
