package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.model.PriceAlert
import com.example.composecraft.features.pulseinvest.domain.model.PulseNotification
import com.example.composecraft.features.pulseinvest.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val getPriceAlertsUseCase: GetPriceAlertsUseCase,
    private val markAllReadUseCase: MarkAllNotificationsReadUseCase,
    private val toggleAlertUseCase: ToggleAlertUseCase
) : ViewModel() {

    val notifications: StateFlow<List<PulseNotification>> =
        getNotificationsUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val alerts: StateFlow<List<PriceAlert>> =
        getPriceAlertsUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun markAllRead() = viewModelScope.launch { markAllReadUseCase() }
    fun toggleAlert(id: String, enabled: Boolean) = viewModelScope.launch { toggleAlertUseCase(id, enabled) }
}
