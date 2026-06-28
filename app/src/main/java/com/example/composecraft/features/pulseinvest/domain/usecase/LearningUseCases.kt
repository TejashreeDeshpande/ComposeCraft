package com.example.composecraft.features.pulseinvest.domain.usecase

import com.example.composecraft.features.pulseinvest.domain.model.LearningProgress
import com.example.composecraft.features.pulseinvest.domain.repository.LearningRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLearningProgressUseCase @Inject constructor(private val repo: LearningRepository) {
    operator fun invoke(): Flow<LearningProgress> = repo.getLearningProgress()
}
