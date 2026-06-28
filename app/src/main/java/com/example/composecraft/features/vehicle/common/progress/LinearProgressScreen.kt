package com.example.composecraft.features.vehicle.common.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun LinearProgressScreenPreview() {
    LinearProgressContent(
        progress = 0.4f,
        currentDistance = 22.0f,
        targetDistance = 55.0f,
        onClickStart = {},
        onClickStop = {}
    )
}

@Composable
fun LinearProgressScreen(
    viewModel: ProgressBarViewModel = koinViewModel(),
) {
    val progressValue by viewModel.progress.collectAsStateWithLifecycle()
    val currentDistance by viewModel.currentDistance.collectAsStateWithLifecycle()
    val targetDistance by viewModel.targetDistance.collectAsStateWithLifecycle()

    LinearProgressContent(
        progress = progressValue,
        currentDistance = currentDistance,
        targetDistance = targetDistance,
        onClickStart = { viewModel.startTrip(distance = 55.0f) },
        onClickStop = { viewModel.stop() }
    )
}

@Composable
fun LinearProgressContent(
    progress: Float,
    currentDistance: Float,
    targetDistance: Float,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Vehicle Trip Progress",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            TripStatsRow(progress, currentDistance, targetDistance)

            Spacer(modifier = Modifier.height(12.dp))

            TripProgressBar(progress)

            Spacer(modifier = Modifier.height(48.dp))

            TripActionButtons(onClickStart, onClickStop, currentDistance)
        }
    }
}

@Composable
private fun TripStatsRow(
    progress: Float,
    currentDistance: Float,
    targetDistance: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(
                text = "Distance",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            DistanceValueText(currentDistance, targetDistance)
        }
        PercentageText(progress)
    }
}

@Composable
private fun DistanceValueText(currentDistance: Float, targetDistance: Float) {
    Text(
        text = "%.1f / %.1f km".format(currentDistance, targetDistance),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun PercentageText(progress: Float) {
    Text(
        text = "${(progress * 100).toInt()}%",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
private fun TripProgressBar(progress: Float) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "progress"
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
    )
}

@Composable
private fun TripActionButtons(
    onClickStart: () -> Unit,
    onClickStop: () -> Unit,
    currentDistance: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onClickStart,
            modifier = Modifier.weight(1f)
        ) {
            Text("Start Trip")
        }
        
        EndTripButton(onClickStop, currentDistance)
    }
}

@Composable
private fun RowScope.EndTripButton(
    onClickStop: () -> Unit, 
    currentDistance: Float
) {
    Button(
        onClick = onClickStop,
        modifier = Modifier.weight(1f),
        enabled = currentDistance > 0
    ) {
        Text("End Trip")
    }
}
