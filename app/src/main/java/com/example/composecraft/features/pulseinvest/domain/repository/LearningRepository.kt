package com.example.composecraft.features.pulseinvest.domain.repository

import com.example.composecraft.features.pulseinvest.domain.model.LearningProgress
import kotlinx.coroutines.flow.Flow

interface LearningRepository {
    fun getLearningProgress(): Flow<LearningProgress>
}
