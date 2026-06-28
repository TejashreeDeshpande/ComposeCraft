package com.example.composecraft.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composecraft.features.animation.*
import com.example.composecraft.features.disastercommandcenter.presentation.screens.DisasterDashboard
import com.example.composecraft.features.instacart.ui.navigation.InstacartNavGraph
import com.example.composecraft.features.fittrack.screens.CreateWorkout
import com.example.composecraft.features.flightstatus.presentation.screens.FlightStatusRoute
import com.example.composecraft.features.pulseinvest.ui.navigation.PulseNavGraph
import com.example.composecraft.features.pulseinvest.ui.theme.PulseTheme
import com.example.composecraft.features.vehicle.common.progress.ProgressScreen
import com.example.composecraft.features.vehicle.notification.NotificationsScreen
import com.example.composecraft.features.vehicle.notification.NotificationsViewModel
import com.example.composecraft.features.vehicle.ridestatus.RideStatusScreen
import com.example.composecraft.features.vehicle.ridestatus.RideStatusViewModel
import com.example.composecraft.features.vehicle.safetycontrols.SafetyControlScreen
import com.example.composecraft.features.vehicle.safetycontrols.SafetyViewModel
import com.example.composecraft.features.vehicle.sensors.SensorDashboardScreen
import com.example.composecraft.features.vehicle.sensors.SensorViewModel
import com.example.composecraft.features.vehicle.triphistory.TripHistoryScreen
import com.example.composecraft.features.vehicle.triphistory.TripHistoryViewModel
import com.example.composecraft.ui.main.AnimationDemoContainer
import com.example.composecraft.ui.main.AnimationsGalleryScreen
import com.example.composecraft.ui.main.FeatureDashboardScreen
import com.example.composecraft.ui.theme.FitTrackTheme
import com.example.composecraft.ui.theme.FlightStatusTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph() {
    val backStack = rememberNavBackStack(DashboardDestination)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<DashboardDestination> {
                FeatureDashboardScreen(onFeatureClick = { destination ->
                    backStack.add(destination)
                })
            }

            entry<SensorDestination> {
                val viewModel: SensorViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                SensorDashboardScreen(
                    uiState = uiState,
                    onSensorCardTap = { viewModel.toggleSensorExpansion(it) },
                    onBack = { backStack.removeAt(backStack.size - 1) }
                )
            }

            entry<RideStatusDestination> {
                val viewModel: RideStatusViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                RideStatusScreen(
                    uiState = uiState,
                    onBack = { backStack.removeAt(backStack.size - 1) },
                    onContact = { },
                    onCancelRide = { viewModel.cancelRide() }
                )
            }

            entry<VehicleNotificationsDestination> {
                val viewModel: NotificationsViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                NotificationsScreen(
                    uiState = uiState,
                    onMarkAllRead = { viewModel.onMarkAllRead() },
                    onDismiss = { viewModel.onDismissNotification(it) },
                    onTap = { viewModel.onNotificationTapped(it) },
                    onBack = { backStack.removeAt(backStack.size - 1) }
                )
            }

            entry<SafetyControlDestination> {
                val viewModel: SafetyViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                SafetyControlScreen(
                    uiState = uiState,
                    onSafetyAction = { viewModel.onSafetyAction(it) },
                    onConfirmPullOver = { viewModel.onConfirmPullOver(it) },
                    onSosHoldStart = { viewModel.onSosHoldStart() },
                    onSosHoldEnd = { viewModel.onSosHoldEnd() },
                    onDismiss = { backStack.removeAt(backStack.size - 1) }
                )
            }

            entry<TripHistoryDestination> {
                val viewModel: TripHistoryViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                TripHistoryScreen(
                    uiState = uiState,
                    onRateTrip = { viewModel.rateTrip(it) },
                    onBack = { backStack.removeAt(backStack.size - 1) }
                )
            }

            entry<PulseDestination> {
                PulseTheme {
                    PulseNavGraph(onExit = { backStack.removeAt(backStack.size - 1) })
                }
            }

            entry<FlightDestination> {
                FlightStatusTheme {
                    FlightStatusRoute(onBack = { backStack.removeAt(backStack.size - 1) })
                }
            }

            entry<DisasterDestination> {
                DisasterDashboard(onTeamSelected = {}, onBack = { backStack.removeAt(backStack.size - 1) })
            }

            entry<FitTrackDestination> {
                FitTrackTheme {
                    CreateWorkout(onBack = { backStack.removeAt(backStack.size - 1) })
                }
            }

            entry<VehicleDestination> {
                ProgressScreen(onBack = { backStack.removeAt(backStack.size - 1) })
            }

            entry<InstacartDestination> {
                InstacartNavGraph(onExit = { backStack.removeAt(backStack.size - 1) })
            }

            entry<AnimationsDestination> {
                AnimationsGalleryScreen(
                    onBack = { backStack.removeAt(backStack.size - 1) },
                    onAnimClick = { destination ->
                        backStack.add(destination)
                    }
                )
            }

            entry<DynamicIslandDestination> {
                AnimationDemoContainer("Dynamic Island", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    DynamicIslandDemo()
                }
            }

            entry<RadarDestination> {
                AnimationDemoContainer("Radar / Gesture Galaxy", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    GestureGalaxyDemo()
                }
            }

            entry<WaveDestination> {
                AnimationDemoContainer("Wave Progress", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    WaveProgressDemo()
                }
            }

            entry<MorphingDestination> {
                AnimationDemoContainer("Morphing Card", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    MorphingCardDemo()
                }
            }

            entry<TypewriterDestination> {
                AnimationDemoContainer("Typewriter", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    TypewriterHighlightBurstScreen()
                }
            }

            entry<BubbleDestination> {
                AnimationDemoContainer("AI Thinking Bubble", onBack = { backStack.removeAt(backStack.size - 1) }) {
                    AIThinkingBubbleDemo()
                }
            }
        }
    )
}
