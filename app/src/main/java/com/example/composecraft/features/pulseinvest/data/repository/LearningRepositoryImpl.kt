package com.example.composecraft.features.pulseinvest.data.repository

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.domain.model.LearningProgress
import com.example.composecraft.features.pulseinvest.domain.repository.LearningRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearningRepositoryImpl @Inject constructor(
    private val dataSource: PulseMockDataSource
) : LearningRepository {
    override fun getLearningProgress(): Flow<LearningProgress> = flow {
        emit(dataSource.getLearningProgress())
    }
}
