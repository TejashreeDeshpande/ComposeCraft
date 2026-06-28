package com.example.composecraft.features.pulseinvest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.features.pulseinvest.domain.model.LearningProgress
import com.example.composecraft.features.pulseinvest.domain.usecase.GetLearningProgressUseCase
import kotlinx.coroutines.flow.*

class LearningViewModel(
    private val getLearningProgressUseCase: GetLearningProgressUseCase
) : ViewModel() {

    val progress: StateFlow<LearningProgress?> =
        getLearningProgressUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
