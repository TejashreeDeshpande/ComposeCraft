package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackGradients

@Preview
@Composable
fun PreviewExerciseDetails() {
    ExerciseDetails()
}

@Composable
fun ExerciseDetails() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Exercise Details",
                subTitle = "",
                gradient = FitTrackGradients.ExerciseLibrary,
            )
        },
    ) { paddingValues ->
        ExerciseDetailsContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ExerciseDetailsContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text("Exercise Details")
    }
}