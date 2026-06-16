package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackGradients

@Preview
@Composable
fun PreviewPlanOverview() {
    PlanOverview()
}

@Composable
fun PlanOverview() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Push Day A",
                subTitle = "Workout plan - 3 blocks",
                gradient = FitTrackGradients.WorkoutBuilder,
            )
        },
    ) { paddingValues ->
        PlanOverviewContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun PlanOverviewContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text("Plan Overview")
    }
}