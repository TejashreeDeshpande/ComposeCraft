package com.example.composecraft.presentation.features.fittrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecraft.presentation.features.fittrack.components.AppTopBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecraft.data.fittrack.MockWorkouts
import com.example.composecraft.data.fittrack.Workout
import com.example.composecraft.presentation.features.fittrack.components.AppButton
import com.example.composecraft.presentation.features.fittrack.components.WorkoutListItem

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "FitTrack",
                subTitle = "Monday, Feb 20",
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier,
                actionButtonIcon = " 👤",
                onClickActionButton = { },
                onClickBackButton = { }
            )
        }
    ) { paddingValues ->
        HomeScreenContents(
            workouts = MockWorkouts.list,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun HomeScreenContents(
    workouts: List<Workout>,
    modifier: Modifier = Modifier
) {
    Column {
        AppButton(
            label = "+ New Workout",
            backgroundColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(workouts) { workout ->
                WorkoutListItem(
                    title = workout.name,
                    subTitle = workout.details,
                    iconStr = workout.status.iconStr,
                    iconBackground = workout.status.backgroundColor,
                    modifier = Modifier.fillMaxWidth(),
                    onClickIcon = { }
                )

            }
        }
    }
}