package com.example.composecraft.features.pulseinvest.domain.model

data class PulseNotification(
    val id: String,
    val title: String,
    val body: String,
    val timeAgo: String,
    val isRead: Boolean = false,
    val emoji: String = "🔔"
)
