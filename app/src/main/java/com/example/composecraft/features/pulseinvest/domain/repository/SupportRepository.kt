package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.SupportMessage
import kotlinx.coroutines.flow.Flow

interface SupportRepository {
    fun getMessages(): Flow<List<SupportMessage>>
    suspend fun sendMessage(text: String)
}
