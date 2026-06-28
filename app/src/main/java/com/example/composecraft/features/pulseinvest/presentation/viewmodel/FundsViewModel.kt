package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.usecase.*
import com.example.composecraft.features.pulseinvest.presentation.state.FundsState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FundsViewModel(
    private val depositFundsUseCase: DepositFundsUseCase,
    private val withdrawFundsUseCase: WithdrawFundsUseCase,
    private val getAvailableBalanceUseCase: GetAvailableBalanceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FundsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(availableBalance = getAvailableBalanceUseCase()) }
        }
    }

    fun onAmountChange(v: String) = _state.update { it.copy(amount = v) }
    fun onMethodChange(m: String) = _state.update { it.copy(selectedMethod = m) }

    fun deposit() = viewModelScope.launch {
        val amount = _state.value.amount.toDoubleOrNull() ?: return@launch
        _state.update { it.copy(isLoading = true) }
        depositFundsUseCase(amount, _state.value.selectedMethod)
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }

    fun withdraw() = viewModelScope.launch {
        val amount = _state.value.amount.toDoubleOrNull() ?: return@launch
        _state.update { it.copy(isLoading = true) }
        withdrawFundsUseCase(amount, _state.value.selectedMethod)
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }

    fun resetSuccess() = _state.update { it.copy(isSuccess = false) }
}
