package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Equipment
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.FTLabelSwitch
import com.example.composecraft.presentation.features.fittrack.components.FTMultiSelectableChipGroup
import com.example.composecraft.presentation.features.fittrack.components.FTTextField
import com.example.composecraft.presentation.features.fittrack.components.FTTitle
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewAddExerciseBasicInfo() {
    FitTrackTheme {
        AddExerciseBasicInfo()
    }
}

@Composable
fun AddExerciseBasicInfo() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "New Exercise",
                subTitle = "Step 1 of 2 - Basic Info",
                colors = FTTopAppBarColors.primary(),
            )
        },
    ) { paddingValues ->
        AddExerciseBasicInfoContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AddExerciseBasicInfoContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FTTextField(
                title = "Exercise Name",
                value = "Romanian Deadlift",
                onValueChanged = {}
            )
            FTTextField(
                title = "Description",
                value = "Hip-hinge movement keeping bar close to legs.",
                onValueChanged = {}
            )
            FTTitle("Equipment")
            FTMultiSelectableChipGroup(
                chipItems = Equipment.entries.map { it.title },
                selectedChips = listOf(Equipment.RESISTANCE_BAND.title),
                onItemSelected = {}
            )
            FTTitle("Exercise Type")
            FTMultiSelectableChipGroup(
                chipItems = ExerciseType.entries.map { it.title },
                selectedChips = listOf(ExerciseType.BENCH_PRESS.title),
                onItemSelected = {}
            )
            FTLabelSwitch(
                title = "Track Weight",
                desc = "Allow weight to be logged",
            )
            FTAppButton(text = "Continue", onClick = {}, modifier = Modifier.fillMaxWidth())
        }

    }
}