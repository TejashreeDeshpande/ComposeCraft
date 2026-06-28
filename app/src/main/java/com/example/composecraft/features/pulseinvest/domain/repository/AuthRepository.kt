package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun verifyEmail(email: String): Result<Unit>
    suspend fun setupPin(pin: String): Result<Unit>
    suspend fun enableBiometric(): Result<Unit>
    fun getCurrentUser(): Flow<User?>
    suspend fun logout()
}
