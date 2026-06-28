package com.example.composecraft.features.pulseinvest.domain.model

enum class MessageSender { USER, SUPPORT }

data class SupportMessage(
    val id: String,
    val text: String,
    val sender: MessageSender
)
