package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.FTListRow
import com.example.composecraft.presentation.features.fittrack.components.FTTitle
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackTheme


@Preview
@Composable
fun PreviewShareWorkout() {
    FitTrackTheme {
        ShareWorkout()
    }
}

@Composable
fun ShareWorkout() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Share Workout",
                subTitle = "Send or receive plans",
                colors = FTTopAppBarColors.primary(),
            )
        }
    ) { innerPadding ->
        ShareWorkoutContent(modifier = Modifier.padding(innerPadding))
    }

}

@Composable
fun ShareWorkoutContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WorkoutCard()
        FTTitle(title = "Share Options")

        FTListRow(leadingIconStr = "📳", label = "NFC Transfer", desc = "Tap phones together")
        FTListRow(leadingIconStr = "ᛒ", label = "Bluetooth", desc = "Send to nearby device")
        FTListRow(
            leadingIconStr = "\uD80C\uDD32",
            label = "Copy Link",
            desc = "Share via text or email"
        )

        FTAppButton(text = "Start Sharing", onClick = {}, modifier = Modifier.fillMaxWidth())
    }

}

@Composable
private fun WorkoutCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            FTTitle("Push Day Workout")
            Text("3 exercises - 12 sets - ~45 min")
        }
    }
}