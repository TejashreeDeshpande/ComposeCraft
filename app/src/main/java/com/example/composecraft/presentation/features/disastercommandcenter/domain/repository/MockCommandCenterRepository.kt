package com.example.composecraft.presentation.features.disastercommandcenter.domain.repository

import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockActiveIncidents
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockKpiData
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockTeams
import kotlinx.coroutines.delay

class MockCommandCenterRepository : CommandCenterRepository {
    override suspend fun getKpis(): List<KpiModel> {
        delay(1000)
        return mockKpiData
    }

    override suspend fun getActiveIncidents(): List<Incident> {
        delay(1000)
        return mockActiveIncidents
    }

    override suspend fun getTeams(): List<Team> {
        delay(1000)
        return mockTeams
    }
}