package com.example.composecraft.presentation.features.disastercommandcenter.domain.repository

import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team

interface CommandCenterRepository {
    suspend fun getKpis(): List<KpiModel>
    suspend fun getActiveIncidents() : List<Incident>
    suspend fun getTeams(): List<Team>
}