package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.model.Portfolio
import com.example.composecraft.features.pulseinvest.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(private val repo: PortfolioRepository) {
    operator fun invoke(): Flow<Portfolio> = repo.getPortfolio()
}
