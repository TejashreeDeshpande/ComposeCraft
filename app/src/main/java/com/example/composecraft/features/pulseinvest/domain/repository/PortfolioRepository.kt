package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.Portfolio
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun getPortfolio(): Flow<Portfolio>
}
