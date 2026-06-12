package com.example.composecraft.di

import com.example.composecraft.presentation.features.disastercommandcenter.domain.repository.CommandCenterRepository
import com.example.composecraft.presentation.features.disastercommandcenter.domain.repository.MockCommandCenterRepository
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.CommandCenterViewModel
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases.CommandCenterUseCases
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases.GetActiveIncidentsUseCase
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases.GetKpisUseCase
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases.GetTeamsUseCase
import com.example.composecraft.presentation.features.vehicle.common.progress.ProgressBarViewModel
import com.example.composecraft.presentation.features.vehicle.common.progress.ProgressViewModel
import com.example.composecraft.presentation.features.vehicle.common.tripprogress.TripProgressViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TripProgressViewModel() }
    viewModel { ProgressBarViewModel() }
    viewModel { ProgressViewModel() }

    // Repository
    single<CommandCenterRepository> {
        MockCommandCenterRepository()
    }
    //User Cases
    single {
        GetKpisUseCase(get())
    }
    single {
        GetTeamsUseCase(get())
    }
    single {
        GetActiveIncidentsUseCase(get())
    }
    single {
        CommandCenterUseCases(
            getKpis = get(),
            getActiveIncidents = get(),
            getTeams = get()
        )
    }

    viewModel {
        CommandCenterViewModel(
            useCases = get()
        )
    }
}
