package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.domain.model.User
import com.example.composecraft.features.pulseinvest.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val _currentUser = MutableStateFlow<User?>(null)

    override suspend fun signUp(name: String, email: String, password: String): Result<User> {
        delay(800)
        val user = User(id = "usr_001", name = name, email = email)
        _currentUser.value = user
        return Result.success(user)
    }

    override suspend fun login(email: String, password: String): Result<User> {
        delay(800)
        val user = User(id = "usr_001", name = "Tejashree Patil", email = email)
        _currentUser.value = user
        return Result.success(user)
    }

    override suspend fun verifyEmail(email: String): Result<Unit> {
        delay(500)
        return Result.success(Unit)
    }

    override suspend fun setupPin(pin: String): Result<Unit> {
        delay(300)
        return Result.success(Unit)
    }

    override suspend fun enableBiometric(): Result<Unit> {
        delay(300)
        return Result.success(Unit)
    }

    override fun getCurrentUser(): Flow<User?> = _currentUser

    override suspend fun logout() {
        _currentUser.value = null
    }
}
