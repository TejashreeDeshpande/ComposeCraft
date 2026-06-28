package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.MessageSender
import com.example.composecraft.features.pulseinvest.domain.model.SupportMessage
import com.example.composecraft.features.pulseinvest.domain.repository.SupportRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupportRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : SupportRepository {

    private val _messages = MutableStateFlow(dataSource.getSupportMessages())

    override fun getMessages(): Flow<List<SupportMessage>> = _messages.asStateFlow()

    override suspend fun sendMessage(text: String) {
        val userMsg = SupportMessage(UUID.randomUUID().toString(), text, MessageSender.USER)
        _messages.value = _messages.value + userMsg
        delay(1000)
        val reply = SupportMessage(UUID.randomUUID().toString(),
            "Thanks for reaching out. Our team will look into this shortly.", MessageSender.SUPPORT)
        _messages.value = _messages.value + reply
    }
}
