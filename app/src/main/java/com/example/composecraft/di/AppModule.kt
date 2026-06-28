package com.example.composecraft.di

import androidx.room.Room
import com.example.composecraft.data.fittrack.AppDatabase
import com.example.composecraft.features.disastercommandcenter.domain.repository.CommandCenterRepository
import com.example.composecraft.features.disastercommandcenter.domain.repository.MockCommandCenterRepository
import com.example.composecraft.features.disastercommandcenter.presentation.CommandCenterViewModel
import com.example.composecraft.features.disastercommandcenter.presentation.usercases.CommandCenterUseCases
import com.example.composecraft.features.disastercommandcenter.presentation.usercases.GetActiveIncidentsUseCase
import com.example.composecraft.features.disastercommandcenter.presentation.usercases.GetKpisUseCase
import com.example.composecraft.features.disastercommandcenter.presentation.usercases.GetTeamsUseCase
import com.example.composecraft.features.fittrack.screens.usecases.SaveWorkoutUseCase
import com.example.composecraft.features.fittrack.screens.usecases.WorkoutRepository
import com.example.composecraft.features.fittrack.screens.usecases.WorkoutRepositoryImpl
import com.example.composecraft.features.fittrack.screens.viewmodel.CreateWorkoutViewModel
import com.example.composecraft.features.flightstatus.data.repository.FlightRepository
import com.example.composecraft.features.flightstatus.data.repository.MockFlightRepository
import com.example.composecraft.features.flightstatus.data.usecase.GetFlightStatusUseCase
import com.example.composecraft.features.flightstatus.data.viewmodel.FlightStatusViewModel
import com.example.composecraft.features.vehicle.common.progress.ProgressBarViewModel
import com.example.composecraft.features.vehicle.common.progress.ProgressViewModel
import com.example.composecraft.features.vehicle.common.tripprogress.TripProgressViewModel
import com.example.composecraft.features.vehicle.notification.NotificationsViewModel
import com.example.composecraft.features.vehicle.ridestatus.RideStatusViewModel
import com.example.composecraft.features.vehicle.safetycontrols.SafetyViewModel
import com.example.composecraft.features.vehicle.sensors.SensorViewModel
import com.example.composecraft.features.vehicle.triphistory.TripHistoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<TripProgressViewModel> { TripProgressViewModel() }
    viewModel<ProgressBarViewModel> { ProgressBarViewModel() }
    viewModel<ProgressViewModel> { ProgressViewModel() }
    viewModel<TripHistoryViewModel> { TripHistoryViewModel() }
    viewModel<SensorViewModel> { SensorViewModel() }
    viewModel<RideStatusViewModel> { RideStatusViewModel() }
    viewModel<NotificationsViewModel> { NotificationsViewModel() }
    viewModel<SafetyViewModel> { SafetyViewModel() }

    // Database
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "compose_craft_db",
        ).build()
    }

    single { get<AppDatabase>().workoutDao() }

    // Repository
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<SaveWorkoutUseCase> { SaveWorkoutUseCase(get()) }
    single<CommandCenterRepository> {
        MockCommandCenterRepository()
    }
    //User Cases
    single<GetKpisUseCase> {
        GetKpisUseCase(get<CommandCenterRepository>())
    }
    single<GetTeamsUseCase> {
        GetTeamsUseCase(get<CommandCenterRepository>())
    }
    single<GetActiveIncidentsUseCase> {
        GetActiveIncidentsUseCase(get<CommandCenterRepository>())
    }
    single<CommandCenterUseCases> {
        CommandCenterUseCases(
            getKpis = get(),
            getActiveIncidents = get(),
            getTeams = get(),
        )
    }

    viewModel<CommandCenterViewModel> {
        CommandCenterViewModel(
            useCases = get<CommandCenterUseCases>(),
        )
    }

    viewModel<CreateWorkoutViewModel> {
        CreateWorkoutViewModel(
            saveWorkoutUseCase = get<SaveWorkoutUseCase>(),
        )
    }
    // Flight Status
    single<FlightRepository> {
        MockFlightRepository()
    }
    single<GetFlightStatusUseCase> {
        GetFlightStatusUseCase(get<FlightRepository>())
    }
    viewModel<FlightStatusViewModel> {
        FlightStatusViewModel(
            getFLightStatusUseCase = get<GetFlightStatusUseCase>(),
        )
    }
}
