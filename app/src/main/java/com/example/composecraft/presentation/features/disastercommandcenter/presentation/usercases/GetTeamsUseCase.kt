package com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases

import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import com.example.composecraft.presentation.features.disastercommandcenter.domain.repository.CommandCenterRepository

class GetTeamsUseCase(
    private val repository: CommandCenterRepository
) {
    suspend operator fun invoke(): List<Team> {
        return repository.getTeams()
    }
}