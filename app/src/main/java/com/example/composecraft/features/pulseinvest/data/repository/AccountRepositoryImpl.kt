package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.Statement
import com.example.composecraft.features.pulseinvest.domain.repository.AccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : AccountRepository {
    override fun getStatements(): Flow<List<Statement>> = flow {
        emit(dataSource.getStatements())
    }

    override suspend fun changePin(newPin: String): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun changePassword(current: String, new: String): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }
}
