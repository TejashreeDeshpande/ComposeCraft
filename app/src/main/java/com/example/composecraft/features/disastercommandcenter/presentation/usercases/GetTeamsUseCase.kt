package com.example.composecraft.features.disastercommandcenter.presentation.usercases

import com.example.composecraft.features.disastercommandcenter.data.model.Team
import com.example.composecraft.features.disastercommandcenter.domain.repository.CommandCenterRepository

class GetTeamsUseCase(
    private val repository: CommandCenterRepository
) {
    suspend operator fun invoke(): List<Team> {
        return repository.getTeams()
    }
}