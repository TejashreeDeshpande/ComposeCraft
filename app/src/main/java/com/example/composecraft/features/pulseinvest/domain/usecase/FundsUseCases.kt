package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.repository.FundsRepository
import javax.inject.Inject

class DepositFundsUseCase @Inject constructor(private val repo: FundsRepository) {
    suspend operator fun invoke(amount: Double, method: String): Result<Unit> =
        repo.deposit(amount, method)
}

class WithdrawFundsUseCase @Inject constructor(private val repo: FundsRepository) {
    suspend operator fun invoke(amount: Double, method: String): Result<Unit> =
        repo.withdraw(amount, method)
}

class GetAvailableBalanceUseCase @Inject constructor(private val repo: FundsRepository) {
    suspend operator fun invoke(): Double = repo.getAvailableBalance()
}
