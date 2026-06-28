package com.example.composecraft.features.pulseinvest.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileInitials: String = name.take(2).uppercase()
)
