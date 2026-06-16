package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackGradients

@Preview
@Composable
fun PreviewExerciseSetsEditor() {
    ExerciseSetsEditor()
}

@Composable
fun ExerciseSetsEditor() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Bench Press",
                subTitle = "Planned sets - Block 2",
                gradient = FitTrackGradients.ExerciseLibrary,
            )
        },
    ) { paddingValues ->
        ExerciseDetailsContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}