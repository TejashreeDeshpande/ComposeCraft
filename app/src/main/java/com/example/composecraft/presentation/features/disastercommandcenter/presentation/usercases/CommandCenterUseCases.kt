package com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases

data class CommandCenterUseCases(
    val getKpis: GetKpisUseCase,
    val getActiveIncidents: GetActiveIncidentsUseCase,
    val getTeams: GetTeamsUseCase
)