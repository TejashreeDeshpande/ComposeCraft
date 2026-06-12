package com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases

import com.example.composecraft.presentation.features.disastercommandcenter.domain.repository.CommandCenterRepository
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel

class GetKpisUseCase(
    private val repository: CommandCenterRepository
) {
    suspend operator fun invoke(): List<KpiModel> {
        return repository.getKpis()
    }
}