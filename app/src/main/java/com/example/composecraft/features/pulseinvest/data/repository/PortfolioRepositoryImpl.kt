package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.Portfolio
import com.example.composecraft.features.pulseinvest.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : PortfolioRepository {
    override fun getPortfolio(): Flow<Portfolio> = flow {
        emit(dataSource.getPortfolio())
    }
}
