package com.example.composecraft.features.fittrack.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composecraft.features.fittrack.components.FTAppButton
import com.example.composecraft.features.fittrack.components.FTAppButtonColors
import com.example.composecraft.features.fittrack.components.FTCircleIcon
import com.example.composecraft.features.fittrack.components.FTListItem
import com.example.composecraft.features.fittrack.components.FTTextField
import com.example.composecraft.features.fittrack.components.FTTopAppBar
import com.example.composecraft.features.fittrack.screens.viewmodel.CreateWorkoutEffect
import com.example.composecraft.features.fittrack.screens.viewmodel.CreateWorkoutIntent
import com.example.composecraft.features.fittrack.screens.viewmodel.CreateWorkoutViewModel
import com.example.composecraft.ui.theme.FitTrackGradients
import com.example.composecraft.ui.theme.FitTrackTheme
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun PreviewCreateWorkout() {
    FitTrackTheme {
        CreateWorkout()
    }
}

@Composable
fun CreateWorkout(
    viewModel: CreateWorkoutViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                CreateWorkoutEffect.NavigateBack -> {
                    onBack()
                }

                is CreateWorkoutEffect.ShowMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Create Workout",
                subTitle = "Design your routine",
                gradient = FitTrackGradients.WorkoutBuilder,
                onBack = onBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        CreateWorkoutContent(
            workoutName = state.workoutName,
            onWorkoutNameChanged = {
                viewModel.onIntent(CreateWorkoutIntent.WorkoutNameChanged(it))
            },
            onClickSaveWorkout = {
                viewModel.onIntent(CreateWorkoutIntent.SaveWorkout)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CreateWorkoutContent(
    workoutName: String,
    onWorkoutNameChanged: (String) -> Unit,
    onClickSaveWorkout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FTTextField(
            title = "Workout Name",
            value = workoutName,
            onValueChanged = onWorkoutNameChanged
        )
        ExerciseSuggestionList()
        ActionButtons(
            onClickAddExercise = {},
            onClickSaveWorkout = {
                onClickSaveWorkout()
            })
    }

}

@Composable
private fun ActionButtons(
    onClickAddExercise: () -> Unit,
    onClickSaveWorkout: () -> Unit
) {
    FTAppButton(
        modifier = Modifier.fillMaxWidth(),
        text = "+ Add Exercise",
        onClick = onClickAddExercise,
        colors = FTAppButtonColors.secondary()
    )
    FTAppButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Save Workout",
        onClick = onClickSaveWorkout,
        colors = FTAppButtonColors.primary()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExerciseSuggestionList() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Text(
                text = "Exercises",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(mockExercises) { exercise ->
            
            FTListItem(
                title = exercise.name.title,
                subTitle = exercise.desc,
                trailing = {
                    FTCircleIcon(
                        iconStr = "T",
                        iconSize = 44.dp,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        onClickActionButton = {}
                    )
                }
            )
        }
    }
}

val mockExercises = listOf(
    Exercise(
        name = ExerciseType.BENCH_PRESS, desc = "3 sets x 8-12 reps"
    ), Exercise(
        name = ExerciseType.PUSH_UPS, desc = "3 sets x 8-12 reps"
    )
)

data class Exercise(val name: ExerciseType, val desc: String)

enum class ExerciseType(val title: String) {
    BENCH_PRESS("Bench Press"), PUSH_UPS("Push-ups")
}

enum class Equipment(val title: String) {
    BENCH_PRESS("Bench Press"), PUSH_UPS("Push-ups")
}