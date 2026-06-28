package com.example.composecraft.features.pulseinvest.presentation.state

data class FundsState(
    val availableBalance: Double = 10250.0,
    val amount: String = "",
    val selectedMethod: String = "Bank Transfer",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
