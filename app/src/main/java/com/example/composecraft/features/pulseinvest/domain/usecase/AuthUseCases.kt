package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.model.User
import com.example.composecraft.features.pulseinvest.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<User> =
        repo.signUp(name, email, password)
}

class LoginUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> =
        repo.login(email, password)
}

class SetupPinUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(pin: String): Result<Unit> = repo.setupPin(pin)
}

class EnableBiometricUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(): Result<Unit> = repo.enableBiometric()
}

class GetCurrentUserUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(): Flow<User?> = repo.getCurrentUser()
}

class LogoutUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.logout()
}
