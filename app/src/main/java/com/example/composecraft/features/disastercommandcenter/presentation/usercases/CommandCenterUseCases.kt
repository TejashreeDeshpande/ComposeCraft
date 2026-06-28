package com.example.composecraft.features.disastercommandcenter.presentation.usercases

data class CommandCenterUseCases(
    val getKpis: GetKpisUseCase,
    val getActiveIncidents: GetActiveIncidentsUseCase,
    val getTeams: GetTeamsUseCase
)