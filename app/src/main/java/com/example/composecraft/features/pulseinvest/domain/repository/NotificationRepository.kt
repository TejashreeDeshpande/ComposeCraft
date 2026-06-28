package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.PriceAlert
import com.example.composecraft.features.pulseinvest.domain.model.PulseNotification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getNotifications(): Flow<List<PulseNotification>>
    fun getPriceAlerts(): Flow<List<PriceAlert>>
    suspend fun markAllRead()
    suspend fun toggleAlert(alertId: String, enabled: Boolean)
}
