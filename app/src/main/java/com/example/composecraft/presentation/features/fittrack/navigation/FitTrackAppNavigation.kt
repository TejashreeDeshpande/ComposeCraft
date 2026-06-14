package com.example.composecraft.presentation.features.fittrack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composecraft.presentation.features.fittrack.screens.CreateWorkout
import com.example.composecraft.presentation.features.fittrack.screens.FitTrackHome
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FitTrackAppNavigation() {
    val backStack = rememberNavBackStack(WorkoutListDestination)
    Scaffold { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            entryProvider = entryProvider {
                entry<WorkoutListDestination> {
                    FitTrackHome(onClickCreateWorkout = {
                        backStack.add(CreateNewWorkoutDestination)
                    })
                }
                entry<CreateNewWorkoutDestination> {
                    CreateWorkout(
                        onClickBackButton = {
                            backStack.remove(CreateNewWorkoutDestination)
                        },
                        onClickSaveWorkout = {},
                        onClickAddExercise = {},
                    )
                }
            }
        )
    }
}

@Serializable
data object WorkoutListDestination : NavKey

@Serializable
data object CreateNewWorkoutDestination : NavKey