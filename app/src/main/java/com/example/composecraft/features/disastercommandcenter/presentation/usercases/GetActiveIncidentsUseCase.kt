package com.example.composecraft.features.disastercommandcenter.presentation.usercases

import com.example.composecraft.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.features.disastercommandcenter.data.model.IncidentType
import com.example.composecraft.features.disastercommandcenter.domain.repository.CommandCenterRepository

class GetActiveIncidentsUseCase(
    private val repository: CommandCenterRepository
) {
    suspend operator fun invoke(
        selectedType: IncidentType? = null
    ): List<Incident> {
        val incidents = repository.getActiveIncidents()
        return selectedType?.let { type ->
            incidents.filter { it.type == type }
        } ?: incidents
    }
}