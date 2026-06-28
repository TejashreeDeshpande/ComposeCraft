package com.example.composecraft.features.disastercommandcenter.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composecraft.features.disastercommandcenter.data.model.BottomBarCard
import com.example.composecraft.features.disastercommandcenter.data.model.Team
import com.example.composecraft.features.disastercommandcenter.data.model.bottomBarCards
import com.example.composecraft.features.disastercommandcenter.presentation.composables.AppBottomBar
import com.example.composecraft.features.disastercommandcenter.presentation.screens.AlertsScreen
import com.example.composecraft.features.disastercommandcenter.presentation.screens.ChatScreen
import com.example.composecraft.features.disastercommandcenter.presentation.screens.DisasterDashboard
import com.example.composecraft.features.disastercommandcenter.presentation.screens.LogScreen
import com.example.composecraft.features.disastercommandcenter.presentation.screens.TeamDetails
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(DisasterDashboardDestination)
    val currentDestination = backStack.last()

    Scaffold(
        bottomBar = {
            val selectedTabIndex = when (currentDestination) {
                is DisasterDashboardDestination -> 0
                is ChatDestination -> 1
                is AlertsDestination -> 2
                is LogDestination -> 3
                else -> 0
            }
            AppBottomBar(
                tabs = bottomBarCards,
                selectedCard = bottomBarCards[selectedTabIndex],
                onTabSelected = { card ->
                    val destination = when (bottomBarCards.indexOf(card)) {
                        0 -> DisasterDashboardDestination
                        1 -> ChatDestination
                        2 -> AlertsDestination
                        3 -> LogDestination
                        else -> DisasterDashboardDestination
                    }
                    if (currentDestination != destination) {
                        backStack.clear()
                        backStack.add(destination)
                    }
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            entryProvider = entryProvider {
                entry<DisasterDashboardDestination> {
                    DisasterDashboard(
                        onTeamSelected = { team ->
                            backStack.add(TeamDetailsDestination(team))
                        }
                    )
                }
                entry<ChatDestination> {
                    ChatScreen()
                }
                entry<AlertsDestination> {
                    AlertsScreen()
                }
                entry<LogDestination> {
                    LogScreen()
                }
                entry<TeamDetailsDestination> { destination ->
                    TeamDetails(team = destination.team)
                }
            },
            transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                        scaleOut(targetScale = .9f) + fadeOut(targetAlpha = .5f)
            },
            popTransitionSpec = {
                scaleIn(initialScale = .9f) + fadeIn(initialAlpha = .5f) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
                scaleIn(initialScale = .9f) + fadeIn(initialAlpha = .5f) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            }
        )
    }
}

@Serializable
data object DisasterDashboardDestination : NavKey

@Serializable
data object ChatDestination : NavKey

@Serializable
data object AlertsDestination : NavKey

@Serializable
data object LogDestination : NavKey

@Serializable
data object MoreDestination : NavKey

@Serializable
data class TeamDetailsDestination(val team: Team) : NavKey