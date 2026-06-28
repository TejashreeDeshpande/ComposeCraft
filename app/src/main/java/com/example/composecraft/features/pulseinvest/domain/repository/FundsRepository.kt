package com.example.composecraft.features.pulseinvest.domain.repository

interface FundsRepository {
    suspend fun deposit(amount: Double, method: String): Result<Unit>
    suspend fun withdraw(amount: Double, method: String): Result<Unit>
    suspend fun getAvailableBalance(): Double
}
