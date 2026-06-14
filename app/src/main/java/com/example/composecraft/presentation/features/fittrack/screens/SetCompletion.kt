package com.example.composecraft.presentation.features.fittrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.ButtonType
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTCard
import com.example.composecraft.presentation.features.fittrack.components.FTListHeader
import com.example.composecraft.ui.theme.FitTrackTheme
import com.example.composecraft.ui.theme.FontSize

@Preview
@Composable
fun PreviewSetCompletion() {
    FitTrackTheme {
        SetCompletion()
    }
}

@Composable
fun SetCompletion() {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Complete Sets",
                subTitle = "Mark individual sets",
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier,
                actionButtonIcon = "",
                onClickActionButton = {},
                onClickBackButton = {}
            )
        }
    ) { innerPadding ->
        SetCompletionContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SetCompletionContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BenchPressMetricCompleted()
        QuickActions()
    }
}

@Composable
fun QuickActions(
    modifier: Modifier = Modifier
) {
    FTCard {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FTListHeader(title = "Quick Actions")

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FTAppButton(
                    text = "Rest Timer",
                    onClick = {},
                    type = ButtonType.Tertiary
                )
                FTAppButton(
                    text = "Notes",
                    onClick = {},
                    type = ButtonType.Tertiary
                )
                FTAppButton(
                    text = "History",
                    onClick = {},
                    type = ButtonType.Tertiary,
                )
            }
        }
    }
}

@Composable
fun BenchPressMetricCompleted() {
    FTCard {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FTListHeader("Bench Press")

            BenchPressStatusRow(
                label = "Set 1: 80kg x 12 reps",
                isChecked = true,
                onCheckedChange = {}
            )

            HorizontalDivider()

            BenchPressStatusRow(
                label = "Set 2: 85kg x 10 reps",
                isChecked = true,
                onCheckedChange = {}
            )

            HorizontalDivider()

            BenchPressStatusRow(
                label = "Set 3: 85kg x 8 reps",
                isChecked = true,
                onCheckedChange = {}
            )
            HorizontalDivider()

            BenchPressStatusRow(
                label = "Set 4: 80kg x 10 reps",
                isChecked = true,
                onCheckedChange = {}
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FTAppButton(
                    text = "Add Set",
                    onClick = {},
                    type = ButtonType.Secondary
                )
                FTAppButton(
                    text = "Done",
                    onClick = {},
                    type = ButtonType.Primary
                )
            }
        }
    }
}

@Composable
fun BenchPressStatusRow(
    label: String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = FontSize.TITLE.value,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange() })
    }
}