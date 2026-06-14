package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.ButtonType
import com.example.composecraft.presentation.features.fittrack.components.FTListRow
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewCreateWorkout() {
    FitTrackTheme {
        CreateWorkout(
            onClickBackButton = {},
            onClickSaveWorkout = {},
            onClickAddExercise = {})
    }
}

@Composable
fun CreateWorkout(
    onClickBackButton: () -> Unit, onClickSaveWorkout: () -> Unit, onClickAddExercise: () -> Unit
) {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Create Workout",
                subTitle = "Design your routine",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier,
                actionButtonIcon = "💻",
                actionButtonIconSize = 44.dp,
                onClickActionButton = { },
                onClickBackButton = onClickBackButton
            )
        },
    ) { paddingValues ->
        CreateWorkoutContent(
            onClickSaveWorkout = onClickSaveWorkout,
            onClickAddExercise = onClickAddExercise,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CreateWorkoutContent(
    onClickSaveWorkout: () -> Unit, onClickAddExercise: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EnterWorkoutName()
        ExerciseSuggestionList()
        ActionButtons(onClickAddExercise, onClickSaveWorkout)
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
        type = ButtonType.Secondary
    )
    FTAppButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Save Workout",
        onClick = onClickSaveWorkout,
        type = ButtonType.Primary
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
                label = exercise.name.label,
                desc = exercise.desc,
                trailingIconStr = "x"
            )
        }
    }
}

@Composable
private fun EnterWorkoutName() {
    Text(
        text = "Workout Name",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
    OutlinedTextField(
        value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth()
    )
}

val mockExercises = listOf(
    Exercise(
        name = ExerciseType.BENCH_PRESS, desc = "3 sets x 8-12 reps"
    ), Exercise(
        name = ExerciseType.PUSH_UPS, desc = "3 sets x 8-12 reps"
    )
)

data class Exercise(val name: ExerciseType, val desc: String)

enum class ExerciseType(val label: String) {
    BENCH_PRESS("Bench Press"), PUSH_UPS("Push-ups")
}