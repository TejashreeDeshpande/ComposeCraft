package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTCard
import com.example.composecraft.presentation.features.fittrack.components.FTListHeader
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.ui.theme.FitTrackTheme
import com.example.composecraft.ui.theme.FontSize

@Preview
@Composable
fun PreviewProfile() {
    FitTrackTheme {
        Profile()
    }
}

@Composable
fun Profile() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Alex",
                subTitle = "Fitness Enthusiast",
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier,
                actionButtonIcon = " ⚙\uFE0F ",
                onClickActionButton = {},
                onClickBackButton = null
            )
        }

    ) { innerPadding ->
        ProfileContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        WorkoutStatusRow()
        SettingsCard()
    }

}

@Composable
fun SettingsCard() {
    FTCard {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FTListHeader("Settings")
            SettingsRow(label = "Share via NFC")
            HorizontalDivider()
            SettingsRow("Bluetooth Sync")
            HorizontalDivider()
            SettingsRow("Exercise Database")
            HorizontalDivider()
            SettingsRow("Export Data")
        }
    }
}

@Composable
private fun SettingsRow(label: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            label,
            fontSize = FontSize.HEADER.value,
            modifier = Modifier.weight(1f)
        )
        Text(
            ">",
            fontSize = FontSize.HEADER.value,
        )
    }
}

@Composable
private fun WorkoutStatusRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FTCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            DataColumn(value = 12, label = "Workout")
        }
        FTCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            DataColumn(value = 5, label = "Week Streak")
        }
        FTCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            DataColumn(value = 23, label = "Exercises")
        }
    }
}

@Composable
private fun DataColumn(value: Int, label: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value.toString(), style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(text = label, style = MaterialTheme.typography.titleMedium)
    }
}