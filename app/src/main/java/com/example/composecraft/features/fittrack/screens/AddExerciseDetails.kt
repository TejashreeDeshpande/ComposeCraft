package com.example.composecraft.features.fittrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.features.disastercommandcenter.data.model.FocusArea
import com.example.composecraft.features.disastercommandcenter.data.model.PrimaryMuscleGroups
import com.example.composecraft.features.fittrack.components.FTFilterChipColors
import com.example.composecraft.features.fittrack.components.FTMultiSelectableChipGroup
import com.example.composecraft.features.fittrack.components.FTSingleSelectableChipGroup
import com.example.composecraft.features.fittrack.components.FTTextColor
import com.example.composecraft.features.fittrack.components.FTTextField
import com.example.composecraft.features.fittrack.components.FTTextStyle
import com.example.composecraft.features.fittrack.components.FTTitle
import com.example.composecraft.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackGradients
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewAddExerciseDetails() {
    FitTrackTheme {
        AddExerciseDetails()
    }
}

@Composable
fun AddExerciseDetails() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "New Exercise",
                subTitle = "Step 1 of 2 - Basic Info",
                gradient = FitTrackGradients.ExerciseLibrary,
            )
        },
    ) { paddingValues ->
        AddExerciseDetailsContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AddExerciseDetailsContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FTTitle(
            "Primary Muscle Groups",
            style = FTTextStyle.rowTitle,
            color = FTTextColor.rowTitle
        )

        FTMultiSelectableChipGroup(
            chipItems = PrimaryMuscleGroups.entries.map { it.title },
            selectedChips = listOf(PrimaryMuscleGroups.HAMSTRINGS.title),
            onItemSelected = {},
            modifier = Modifier.fillMaxWidth()
        )

        FTTitle(
            "Difficulty Level",
            style = FTTextStyle.rowTitle,
            color = FTTextColor.rowTitle
        )

        FTSingleSelectableChipGroup(
            chipItems = FocusArea.entries.map { it.title },
            selectedChip = FocusArea.ALL.title,
            onItemSelected = {},
            shape = RoundedCornerShape(8.dp),
            colors = FTFilterChipColors.secondary()
        )

        FTTextField(
            title = "Video URL (optional)",
            value = "",
            onValueChanged = {}
        )

        ActionRow()
    }
}

@Composable
fun ActionRow() {
    Row {


    }
}