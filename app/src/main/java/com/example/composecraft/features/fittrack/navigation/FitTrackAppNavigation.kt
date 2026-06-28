package com.example.composecraft.features.fittrack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composecraft.features.fittrack.screens.AddExerciseBasicInfo
import com.example.composecraft.features.fittrack.screens.AddExerciseDetails
import com.example.composecraft.features.fittrack.screens.BlockEditor
import com.example.composecraft.features.fittrack.screens.CreateWorkout
import com.example.composecraft.features.fittrack.screens.ExerciseDetails
import com.example.composecraft.features.fittrack.screens.ExerciseLibrary
import com.example.composecraft.features.fittrack.screens.ExerciseSetsEditor
import com.example.composecraft.features.fittrack.screens.Home
import com.example.composecraft.features.fittrack.screens.PlanOverview
import com.example.composecraft.features.fittrack.screens.Profile
import com.example.composecraft.features.fittrack.screens.SetCompletion
import com.example.composecraft.features.fittrack.screens.SetTracking
import com.example.composecraft.features.fittrack.screens.ShareWorkout
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FitTrackAppNavigation() {
    val backStack = rememberNavBackStack(HomeDestination)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val toggleDrawer = remember(drawerState, scope) {
        {
            scope.launch {
                if (drawerState.isClosed) {
                    drawerState.open()
                } else {
                    drawerState.close()
                }
            }
            Unit
        }
    }

    CompositionLocalProvider(LocalNavigationRailToggle provides toggleDrawer) {
        FitTrackNavigationShell(
            backStack = backStack,
            drawerState = drawerState,
            onCloseDrawer = {
                scope.launch { drawerState.close() }
            }
        ) {
            NavDisplay(
                modifier = Modifier.fillMaxSize(),
                backStack = backStack,
                entryProvider = entryProvider {
                    entry<HomeDestination> { _ ->
                        Home(
                            navigateTo = { destination -> backStack.add(destination) },
                            onClickMenu = toggleDrawer
                        )
                    }
                    entry<CreateNewWorkoutDestination> { _ ->
                        CreateWorkout()
                    }
                    entry<SetTrackingDestination> { _ ->
                        SetTracking(
                            onClickBackButton = {
                                backStack.remove(SetTrackingDestination)
                            },
                        )
                    }
                    entry<SetCompletionDestination> { _ ->
                        SetCompletion()
                    }
                    entry<ShareWorkoutDestination> { _ ->
                        ShareWorkout()
                    }
                    entry<ProfileDestination> { _ ->
                        Profile()
                    }
                    entry<ExerciseLibraryDestination> { _ ->
                        ExerciseLibrary()
                    }
                    entry<AddExerciseBasicInfoDestination> { _ ->
                        AddExerciseBasicInfo()
                    }
                    entry<AddExerciseDetailsDestination> { _ ->
                        AddExerciseDetails()
                    }
                    entry<ExerciseDetailsDestination> { _ ->
                        ExerciseDetails()
                    }
                    entry<PlanOverviewDestination> { _ ->
                        PlanOverview()
                    }
                    entry<BlockEditorDestination> { _ ->
                        BlockEditor()
                    }
                    entry<ExerciseSetsEditorDestination> { _ ->
                        ExerciseSetsEditor()
                    }
                }
            )
        }
    }
}

@Composable
fun FitTrackNavigationShell(
    backStack: NavBackStack<NavKey>,
    drawerState: DrawerState,
    onCloseDrawer: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    fun navigateTo(destination: NavKey) {
        if (backStack.lastOrNull() != destination) {
            backStack.add(destination)
        }
        onCloseDrawer()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationRail(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == HomeDestination,
                        onClick = { navigateTo(HomeDestination) },
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text("Home") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == CreateNewWorkoutDestination,
                        onClick = { navigateTo(CreateNewWorkoutDestination) },
                        icon = { Icon(Icons.Default.FitnessCenter, null) },
                        label = { Text("Create Workout") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == SetTrackingDestination,
                        onClick = { navigateTo(SetTrackingDestination) },
                        icon = { Icon(Icons.Default.TrackChanges, null) },
                        label = { Text("Set Tracking") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == SetCompletionDestination,
                        onClick = { navigateTo(SetCompletionDestination) },
                        icon = { Icon(Icons.Default.CheckCircleOutline, null) },
                        label = { Text("Set Completion") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == ShareWorkoutDestination,
                        onClick = { navigateTo(ShareWorkoutDestination) },
                        icon = { Icon(Icons.Default.Share, null) },
                        label = { Text("Share Workout") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == ProfileDestination,
                        onClick = { navigateTo(ProfileDestination) },
                        icon = { Icon(Icons.Default.Person, null) },
                        label = { Text("Profile") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == ExerciseLibraryDestination,
                        onClick = { navigateTo(ExerciseLibraryDestination) },
                        icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, null) },
                        label = { Text("Exercise Library") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == AddExerciseBasicInfoDestination,
                        onClick = { navigateTo(AddExerciseBasicInfoDestination) },
                        icon = { Icon(Icons.Default.AddCircleOutline, null) },
                        label = { Text("Add Exercise 1") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == AddExerciseDetailsDestination,
                        onClick = { navigateTo(AddExerciseDetailsDestination) },
                        icon = { Icon(Icons.Default.AddCircleOutline, null) },
                        label = { Text("Add Exercise 2") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == ExerciseDetailsDestination,
                        onClick = { navigateTo(ExerciseDetailsDestination) },
                        icon = { Icon(Icons.Default.Info, null) },
                        label = { Text("Exercise Details") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == PlanOverviewDestination,
                        onClick = { navigateTo(PlanOverviewDestination) },
                        icon = { Icon(Icons.AutoMirrored.Filled.Assignment, null) },
                        label = { Text("Plan Overview") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == BlockEditorDestination,
                        onClick = { navigateTo(BlockEditorDestination) },
                        icon = { Icon(Icons.Default.ViewAgenda, null) },
                        label = { Text("Block Editor") }
                    )

                    NavigationRailItem(
                        selected = backStack.lastOrNull() == ExerciseSetsEditorDestination,
                        onClick = { navigateTo(ExerciseSetsEditorDestination) },
                        icon = { Icon(Icons.Default.FormatListNumbered, null) },
                        label = { Text("Exercise Sets Editor") }
                    )
                }
            }
        },
        content = content
    )
}

@Serializable
data object HomeDestination : NavKey

@Serializable
data object CreateNewWorkoutDestination : NavKey

@Serializable
data object SetTrackingDestination : NavKey

@Serializable
data object SetCompletionDestination : NavKey

@Serializable
data object ShareWorkoutDestination : NavKey

@Serializable
data object ProfileDestination : NavKey

@Serializable
data object ExerciseLibraryDestination : NavKey

@Serializable
data object AddExerciseBasicInfoDestination : NavKey

@Serializable
data object AddExerciseDetailsDestination : NavKey

@Serializable
data object ExerciseDetailsDestination : NavKey

@Serializable
data object PlanOverviewDestination : NavKey

@Serializable
data object BlockEditorDestination : NavKey

@Serializable
data object ExerciseSetsEditorDestination : NavKey
