package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.FTAppButtonColors
import com.example.composecraft.presentation.features.fittrack.components.FTListRow
import com.example.composecraft.presentation.features.fittrack.components.FTTextField
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackGradients
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewCreateWorkout() {
    FitTrackTheme {
        CreateWorkout()
    }
}

@Composable
fun CreateWorkout() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Create Workout",
                subTitle = "Design your routine",
                gradient= FitTrackGradients.WorkoutBuilder,
            )
        },
    ) { paddingValues ->
        CreateWorkoutContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CreateWorkoutContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FTTextField(
            title = "Workout Name",
            value = "Push Day",
            onValueChanged = {}
        )
        ExerciseSuggestionList()
        ActionButtons(
            onClickAddExercise = {},
            onClickSaveWorkout = {})
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
            FTListRow(
                label = exercise.name.title,
                desc = exercise.desc,
                trailingIconStr = "x"
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