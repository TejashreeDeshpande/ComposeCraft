package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.PriceAlert
import com.example.composecraft.features.pulseinvest.domain.model.PulseNotification
import com.example.composecraft.features.pulseinvest.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : NotificationRepository {

    private val _notifications = MutableStateFlow(dataSource.getNotifications())
    private val _alerts = MutableStateFlow(dataSource.getPriceAlerts())

    override fun getNotifications(): Flow<List<PulseNotification>> = _notifications.asStateFlow()
    override fun getPriceAlerts(): Flow<List<PriceAlert>> = _alerts.asStateFlow()

    override suspend fun markAllRead() {
        _notifications.value = _notifications.value.map { it.copy(isRead = true) }
    }

    override suspend fun toggleAlert(alertId: String, enabled: Boolean) {
        _alerts.value = _alerts.value.map {
            if (it.id == alertId) it.copy(isEnabled = enabled) else it
        }
    }
}
