package com.example.composecraft.features.pulseinvest.di

import com.example.composecraft.features.pulseinvest.data.datasource.PulseMockDataSource
import com.example.composecraft.features.pulseinvest.data.repository.*
import com.example.composecraft.features.pulseinvest.domain.repository.*
import com.example.composecraft.features.pulseinvest.domain.usecase.*
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.*
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// ─── Data Source ─────────────────────────────────────────────────────────────
val dataSourceModule = module {
    single { PulseMockDataSource() }
}

// ─── Repositories ─────────────────────────────────────────────────────────────
val repositoryModule = module {
    single<AuthRepository>         { AuthRepositoryImpl() }
    single<StockRepository>        { StockRepositoryImpl(get()) }
    single<PortfolioRepository>    { PortfolioRepositoryImpl(get()) }
    single<FundsRepository>        { FundsRepositoryImpl() }
    single<LearningRepository>     { LearningRepositoryImpl(get()) }
    single<NotificationRepository> { NotificationRepositoryImpl(get()) }
    single<AccountRepository>      { AccountRepositoryImpl(get()) }
    single<SupportRepository>      { SupportRepositoryImpl(get()) }
}

// ─── Use Cases ────────────────────────────────────────────────────────────────
val useCaseModule = module {
    // Auth
    factory { SignUpUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { SetupPinUseCase(get()) }
    factory { EnableBiometricUseCase(get()) }
    factory { GetCurrentUserUseCase(get()) }
    factory { LogoutUseCase(get()) }

    // Stock
    factory { GetWatchlistUseCase(get()) }
    factory { SearchStocksUseCase(get()) }
    factory { GetStockDetailUseCase(get()) }
    factory { PlaceOrderUseCase(get()) }
    factory { GetNewsUseCase(get()) }
    factory { GetTrendingUseCase(get()) }

    // Portfolio
    factory { GetPortfolioUseCase(get()) }

    // Funds
    factory { DepositFundsUseCase(get()) }
    factory { WithdrawFundsUseCase(get()) }
    factory { GetAvailableBalanceUseCase(get()) }

    // Learning
    factory { GetLearningProgressUseCase(get()) }

    // Notifications
    factory { GetNotificationsUseCase(get()) }
    factory { GetPriceAlertsUseCase(get()) }
    factory { MarkAllNotificationsReadUseCase(get()) }
    factory { ToggleAlertUseCase(get()) }
}

// ─── ViewModels ───────────────────────────────────────────────────────────────
val viewModelModule = module {
    viewModel {
        AuthViewModel(
            signUpUseCase          = get(),
            loginUseCase           = get(),
            setupPinUseCase        = get(),
            enableBiometricUseCase = get(),
        )
    }
    viewModel {
        HomeViewModel(
            getWatchlistUseCase   = get(),
            getPortfolioUseCase   = get(),
            getTrendingUseCase    = get(),
            getCurrentUserUseCase = get(),
        )
    }
    viewModel {
        StockViewModel(
            getStockDetailUseCase      = get(),
            searchStocksUseCase        = get(),
            placeOrderUseCase          = get(),
            getAvailableBalanceUseCase = get(),
        )
    }
    viewModel {
        PortfolioViewModel(
            getPortfolioUseCase  = get(),
            getWatchlistUseCase  = get(),
        )
    }
    viewModel {
        FundsViewModel(
            depositFundsUseCase        = get(),
            withdrawFundsUseCase       = get(),
            getAvailableBalanceUseCase = get(),
        )
    }
    viewModel {
        NotificationViewModel(
            getNotificationsUseCase = get(),
            getPriceAlertsUseCase   = get(),
            markAllReadUseCase      = get(),
            toggleAlertUseCase      = get(),
        )
    }
    viewModel { SupportViewModel(supportRepository = get()) }
    viewModel { AccountViewModel(accountRepository = get()) }
    viewModel { LearningViewModel(getLearningProgressUseCase = get()) }
}

// ─── All modules combined ─────────────────────────────────────────────────────
val pulseModules = listOf(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)
