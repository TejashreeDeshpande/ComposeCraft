package com.example.composecraft.presentation.features.fittrack.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.FTAppButtonColors
import com.example.composecraft.presentation.features.fittrack.components.FTCircleIcon
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTCard
import com.example.composecraft.presentation.features.fittrack.components.FTCardColors
import com.example.composecraft.presentation.features.fittrack.components.FTTitle
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.ui.theme.FitTrackGradients

@Preview
@Composable
fun PreviewSetTracking() {
    SetTracking(onClickBackButton = {})
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetTracking(onClickBackButton: () -> Unit) {
    Scaffold(
        topBar = {
            FTTopAppBar(
                title = "Bench Press",
                subTitle = "Set 2 of 4",
                gradient = FitTrackGradients.WorkoutBuilder,
            )
        }) { innerPadding ->
        SetTrackingContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SetTrackingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SetTrackingMetricSetUp()

        ActionButtons()
    }
}

@Composable
private fun SetTrackingMetricSetUp(modifier: Modifier = Modifier) {
    FTCard(colors = FTCardColors.disabled()) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            FTTitle("Bench Press")

            SetTrackingRow(setNumber = "1")
            HorizontalDivider()
            SetTrackingRow(setNumber = "2")
            HorizontalDivider()
            SetTrackingRow(setNumber = "3")
            HorizontalDivider()
            SetTrackingRow(setNumber = "4")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSetTrackingRow() {
    SetTrackingRow(setNumber = "1")
}

@Composable
private fun SetTrackingRow(
    setNumber: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FTCircleIcon(
            iconStr = setNumber,
            iconSize = 32.dp,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelTextField(
                label = "Weight (kg)",
                value = 80,
                onValueChange = {},
                modifier = Modifier.weight(1f)
            )
            LabelTextField(
                label = "Reps", value = 10, onValueChange = {}, modifier = Modifier.weight(1f)
            )
        }
        Checkbox(
            checked = true, onCheckedChange = {})
    }
}

@Composable
private fun LabelTextField(
    label: String, value: Int, onValueChange: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = if (value == 0) "" else value.toString(),
            onValueChange = { newValue ->
                if (newValue.isEmpty()) {
                    onValueChange(0)
                } else {
                    newValue.toIntOrNull()?.let { parsedInt ->
                        onValueChange(parsedInt)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
            )
        )
    }
}

@Composable
private fun ActionButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FTAppButton(
                modifier = Modifier.weight(1f),
                text = "Mark Sets",
                onClick = {},
                colors = FTAppButtonColors.tertiary()
            )

            FTAppButton(
                modifier = Modifier.weight(1f),
                text = "Complete All",
                onClick = {},
                colors = FTAppButtonColors.secondary()
            )

        }
        FTAppButton(
            text = "New Exercise",
            onClick = {},
            colors = FTAppButtonColors.primary(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
