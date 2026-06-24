package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.FocusArea
import com.example.composecraft.presentation.features.fittrack.components.FTCard
import com.example.composecraft.presentation.features.fittrack.components.FTCardColors
import com.example.composecraft.presentation.features.fittrack.components.FTSingleSelectableChipGroup
import com.example.composecraft.presentation.features.fittrack.components.FTTitle
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackGradients
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewExerciseLibrary() {
    FitTrackTheme {
        ExerciseLibrary()
    }
}

@Composable
fun ExerciseLibrary() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Exercises",
                subTitle = "Your exercise library",
                gradient = FitTrackGradients.ExerciseLibrary,
            )
        }
    ) { innerPadding ->
        ExerciseLibraryContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ExerciseLibraryContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FTSingleSelectableChipGroup(
            chipItems = FocusArea.entries.map { it.title },
            selectedChip = FocusArea.ALL.title,
            onItemSelected = {}
        )
        ExerciseLibraryRow()
    }

}

@Composable
fun ExerciseLibraryRow() {
    Column {
        FTCard(colors = FTCardColors.primary()) {
            Column(modifier = Modifier.padding(16.dp)) {
                FTTitle("Bench Press")
                Text(text = "Barbell - Chest - Triceps, Shoulders")
                Row {
                    AssistChip(
                        onClick = {},
                        enabled = false,
                        label = { Text("Immediate") },
                    )
                    AssistChip(
                        onClick = {},
                        enabled = false,
                        label = { Text("Weight") },
                    )
                }
            }
        }
        FTCard(colors = FTCardColors.disabled()) {
            Column(modifier = Modifier.padding(16.dp)) {
                FTTitle("Pull-up")
                Text(text = "BodyWeight - Back. Biceps")
                Row {
                    AssistChip(onClick = {}, label = { Text("Advanced") })
                }
            }
        }
        FTCard(colors = FTCardColors.disabled()) {
            Column(modifier = Modifier.padding(16.dp)) {
                FTTitle("Bicep Curl")
                Text(text = "Dumbbell - Biceps")
                Row {
                    AssistChip(onClick = {}, label = { Text("Beginner") })
                    AssistChip(onClick = {}, label = { Text("Weight") })
                }
            }
        }
    }

}