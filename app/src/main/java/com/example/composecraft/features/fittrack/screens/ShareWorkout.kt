package com.example.composecraft.features.fittrack.screens

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
import com.example.composecraft.features.fittrack.components.FTAppButton
import com.example.composecraft.features.fittrack.components.FTCircleIcon
import com.example.composecraft.features.fittrack.components.FTListItem
import com.example.composecraft.features.fittrack.components.FTTextColor
import com.example.composecraft.features.fittrack.components.FTTextStyle
import com.example.composecraft.features.fittrack.components.FTTitle
import com.example.composecraft.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackGradients
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
                gradient = FitTrackGradients.Profile,
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
        FTTitle(text = "Share Options")

        FTListItem(
            title = "NFC Transfer",
            subTitle = "Tap phones together",
            leading = {
                FTCircleIcon(
                    iconStr = "📳",
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
        )

        FTListItem(
            title = "Bluetooth",
            subTitle = "Send to nearby device",
            leading = {
                FTCircleIcon(
                    iconStr = "ᛒ",
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
        )

        FTListItem(
            title = "Copy Link",
            subTitle = "Share via text or email",
            leading = {
                FTCircleIcon(
                    iconStr = "\uD80C\uDD32",
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
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
            FTTitle(
                "Push Day Workout",
                style = FTTextStyle.rowTitle,
                color = FTTextColor.rowTitle
            )
            Text("3 exercises - 12 sets - ~45 min")
        }
    }
}