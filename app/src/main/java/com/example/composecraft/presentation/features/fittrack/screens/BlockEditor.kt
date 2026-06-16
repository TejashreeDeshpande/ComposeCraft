package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
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
fun PreviewBlockEditor() {
    BlockEditor()
}

@Composable
fun BlockEditor() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Edit Block",
                subTitle = "Block 2 - Main Work",
                gradient = FitTrackGradients.WorkoutBuilder,
            )
        },
    ) { paddingValues ->
        BlockEditorContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun BlockEditorContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Block Editor")
    }
}