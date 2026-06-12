package com.example.composecraft.presentation.features.vehicle.notification

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// --- Data / State ---

enum class NotificationType { TRIP_UPDATE, PROMO, ALERT }

data class AppNotification(
    val id: String,
    val type: NotificationType,
    val title: String,
    val subtitle: String,
    val relativeTime: String,   // e.g. "2m ago", "1h ago", "1d ago"
    val isRead: Boolean = false
)

data class NotificationsUiState(
    val notifications: List<AppNotification> = emptyList()
) {
    val hasUnread: Boolean
        get() = notifications.any { !it.isRead }

    val unreadCount: Int
        get() = notifications.count { !it.isRead }
}

// --- ViewModel ---

class NotificationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        NotificationsUiState(
            notifications = listOf(
                AppNotification(
                    id = "1",
                    type = NotificationType.TRIP_UPDATE,
                    title = "Ride arriving soon",
                    subtitle = "Your Waymo is 2 minutes away",
                    relativeTime = "2m ago",
                    isRead = false
                ),
                AppNotification(
                    id = "2",
                    type = NotificationType.PROMO,
                    title = "\$5 off your next ride",
                    subtitle = "Use code WAYMO5 before Sunday",
                    relativeTime = "1h ago",
                    isRead = false
                ),
                AppNotification(
                    id = "3",
                    type = NotificationType.ALERT,
                    title = "Service area update",
                    subtitle = "Coverage expanded to Palo Alto",
                    relativeTime = "3h ago",
                    isRead = true
                ),
                AppNotification(
                    id = "4",
                    type = NotificationType.TRIP_UPDATE,
                    title = "Trip completed",
                    subtitle = "Rate your ride to Waymo HQ",
                    relativeTime = "1d ago",
                    isRead = true
                )
            )
        )
    )
    val uiState: StateFlow<NotificationsUiState> = _uiState.asStateFlow()

    fun onMarkAllRead() {
        _uiState.update { current ->
            current.copy(
                notifications = current.notifications.map { it.copy(isRead = true) }
            )
        }
    }

    fun onDismissNotification(id: String) {
        _uiState.update { current ->
            current.copy(
                notifications = current.notifications.filterNot { it.id == id }
            )
        }
    }

    fun onNotificationTapped(id: String) {
        _uiState.update { current ->
            current.copy(
                notifications = current.notifications.map { notif ->
                    if (notif.id == id) notif.copy(isRead = true) else notif
                }
            )
        }
    }
}
