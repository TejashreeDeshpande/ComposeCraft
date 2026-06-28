package com.example.composecraft.features.disastercommandcenter.presentation.usercases

import com.example.composecraft.features.disastercommandcenter.domain.repository.CommandCenterRepository
import com.example.composecraft.features.disastercommandcenter.data.model.KpiModel

class GetKpisUseCase(
    private val repository: CommandCenterRepository
) {
    suspend operator fun invoke(): List<KpiModel> {
        return repository.getKpis()
    }
}