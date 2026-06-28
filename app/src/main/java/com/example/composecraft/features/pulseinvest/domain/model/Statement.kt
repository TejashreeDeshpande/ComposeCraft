package com.example.composecraft.features.pulseinvest.domain.model

enum class StatementType { MONTHLY, TAX }

data class Statement(
    val id: String,
    val label: String,
    val period: String,
    val type: StatementType
)
