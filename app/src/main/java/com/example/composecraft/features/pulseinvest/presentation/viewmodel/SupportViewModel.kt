package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.model.SupportMessage
import com.example.composecraft.features.pulseinvest.domain.repository.SupportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SupportViewModel(
    private val supportRepository: SupportRepository
) : ViewModel() {

    val messages: StateFlow<List<SupportMessage>> =
        supportRepository.getMessages()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    fun onInputChange(v: String) { _inputText.value = v }

    fun sendMessage() = viewModelScope.launch {
        val text = _inputText.value.trim()
        if (text.isBlank()) return@launch
        _inputText.value = ""
        supportRepository.sendMessage(text)
    }
}
