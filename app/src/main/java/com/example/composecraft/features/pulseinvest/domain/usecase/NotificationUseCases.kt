package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.model.PriceAlert
import com.example.composecraft.features.pulseinvest.domain.model.PulseNotification
import com.example.composecraft.features.pulseinvest.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(private val repo: NotificationRepository) {
    operator fun invoke(): Flow<List<PulseNotification>> = repo.getNotifications()
}

class GetPriceAlertsUseCase @Inject constructor(private val repo: NotificationRepository) {
    operator fun invoke(): Flow<List<PriceAlert>> = repo.getPriceAlerts()
}

class MarkAllNotificationsReadUseCase @Inject constructor(private val repo: NotificationRepository) {
    suspend operator fun invoke() = repo.markAllRead()
}

class ToggleAlertUseCase @Inject constructor(private val repo: NotificationRepository) {
    suspend operator fun invoke(alertId: String, enabled: Boolean) =
        repo.toggleAlert(alertId, enabled)
}
