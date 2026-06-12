package com.example.composecraft.presentation.features.fittrack

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.AppTopBar

@Preview
@Composable
fun CreateWorkoutScreenPreview() {
    CreateWorkoutScreen()
}

@Composable
fun CreateWorkoutScreen() {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Create Workout",
                subTitle = "Design your routine",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier,
                navButtonIcon = "←",
                actionButtonIcon = "💻",
                actionButtonIconSize = 44.dp,
                onClickActionButton = { },
                onClickBackButton = { }
            )
        },
    ) { paddingValues ->
        CreateWorkoutScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun CreateWorkoutScreenContent(modifier: Modifier = Modifier) {

}