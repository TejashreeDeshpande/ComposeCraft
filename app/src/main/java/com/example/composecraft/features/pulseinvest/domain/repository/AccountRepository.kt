package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.Statement
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getStatements(): Flow<List<Statement>>
    suspend fun changePin(newPin: String): Result<Unit>
    suspend fun changePassword(current: String, new: String): Result<Unit>
}
