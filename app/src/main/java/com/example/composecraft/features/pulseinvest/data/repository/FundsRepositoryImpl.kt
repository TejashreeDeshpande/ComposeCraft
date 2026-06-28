package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.domain.repository.FundsRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FundsRepositoryImpl @Inject constructor() : FundsRepository {
    private var balance = 10250.00

    override suspend fun deposit(amount: Double, method: String): Result<Unit> {
        delay(1000)
        balance += amount
        return Result.success(Unit)
    }

    override suspend fun withdraw(amount: Double, method: String): Result<Unit> {
        delay(1000)
        return if (amount <= balance) {
            balance -= amount
            Result.success(Unit)
        } else {
            Result.failure(Exception("Insufficient funds"))
        }
    }

    override suspend fun getAvailableBalance(): Double = balance
}
