package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.usecase.*
import com.example.composecraft.features.pulseinvest.presentation.state.AuthState
import com.example.composecraft.features.pulseinvest.presentation.state.UiEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class AuthEffect : UiEffect {
    data class ShowError(val message: String) : AuthEffect()
    data object NavigateToHome : AuthEffect()
}

class AuthViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val setupPinUseCase: SetupPinUseCase,
    private val enableBiometricUseCase: EnableBiometricUseCase
) : ViewModel() {

    private val _effect = Channel<AuthEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onNameChange(v: String) = _state.update { it.copy(name = v) }
    fun onEmailChange(v: String) = _state.update { it.copy(email = v) }
    fun onPasswordChange(v: String) = _state.update { it.copy(password = v) }
    fun onConfirmPasswordChange(v: String) = _state.update { it.copy(confirmPassword = v) }

    fun onPinDigit(digit: String) {
        val cur = _state.value.pin
        if (cur.length < 6) _state.update { it.copy(pin = cur + digit) }
    }

    fun onPinBackspace() {
        val cur = _state.value.pin
        if (cur.isNotEmpty()) _state.update { it.copy(pin = cur.dropLast(1)) }
    }

    fun signUp() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, error = null) }
        signUpUseCase(_state.value.name, _state.value.email, _state.value.password)
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }

    fun login() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, error = null) }
        loginUseCase(_state.value.email, _state.value.password)
            .onSuccess {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            }
            .onFailure { e ->
                val msg = e.message ?: "Login failed"
                _state.update { it.copy(isLoading = false, error = msg) }
                _effect.send(AuthEffect.ShowError(msg))
            }
    }

    fun setupPin() = viewModelScope.launch {
        if (_state.value.pin.length == 6) {
            _state.update { it.copy(isLoading = true) }
            setupPinUseCase(_state.value.pin)
                .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
        }
    }

    fun enableBiometric() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        enableBiometricUseCase()
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }

    fun resetSuccess() = _state.update { it.copy(isSuccess = false) }
    fun clearError()   = _state.update { it.copy(error = null) }
}
