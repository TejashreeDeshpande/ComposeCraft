package com.example.composecraft.presentation.components.jun4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CounterWaterIntake(
    goal: Int,
    currentStatus: Int = 0,
) {
    var counter by rememberSaveable { mutableIntStateOf(currentStatus) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "DAILY WATER INTAKE",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "$counter",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primaryFixedDim
        )
        Row {
            Button(onClick = {
                if (counter != 0)
                    counter -= 1
            }) {
                Text(" - ")
            }
            Spacer(modifier = Modifier.width(4.dp))
            LinearProgressIndicator(
                progress = { (counter.toFloat() / goal.toFloat()).coerceIn(0f, 1f) }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Button(onClick = {
                if (counter < goal)
                    counter += 1
            }) {
                Text(" + ")
            }
        }
        Text(text = "Goal: 8 glasses")
    }
}

@Preview
@Composable
fun CounterWaterIntakePreview() {
    CounterWaterIntake(8)
}