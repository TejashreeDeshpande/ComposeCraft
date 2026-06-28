package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.model.Statement
import com.example.composecraft.features.pulseinvest.domain.repository.AccountRepository
import kotlinx.coroutines.flow.*

class AccountViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val statements: StateFlow<List<Statement>> =
        accountRepository.getStatements()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
