package com.example.composecraft.presentation.features.vehicle.common.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun CircularProgressScreenPreview() {
    CircularProgressContent(
        progress = 0.4f,
        currentDistance = 22.0f,
        targetDistance = 55.0f,
        onClickStart = {},
        onClickStop = {}
    )
}

@Composable
fun CircularProgressScreen(
    viewModel: ProgressBarViewModel = koinViewModel(),
) {
    val progressValue by viewModel.progress.collectAsStateWithLifecycle()
    val currentDistance by viewModel.currentDistance.collectAsStateWithLifecycle()
    val targetDistance by viewModel.targetDistance.collectAsStateWithLifecycle()

    CircularProgressContent(
        progress = progressValue,
        currentDistance = currentDistance,
        targetDistance = targetDistance,
        onClickStart = { viewModel.startTrip(distance = 55.0f) },
        onClickStop = { viewModel.stop() }
    )
}

@Composable
fun CircularProgressContent(
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

            TripProgressBar(progress)

            Spacer(modifier = Modifier.height(48.dp))

            TripStats(currentDistance, targetDistance)

            Spacer(modifier = Modifier.height(48.dp))

            TripActionButtons(onClickStart, onClickStop, currentDistance)
        }
    }
}

@Composable
private fun TripProgressBar(progress: Float) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "progress"
    )

    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(200.dp),
            strokeWidth = 12.dp,
            strokeCap = StrokeCap.Round,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun TripStats(currentDistance: Float, targetDistance: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Distance Covered",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = "%.1f / %.1f km".format(currentDistance, targetDistance),
            style = MaterialTheme.typography.headlineSmall
        )
    }
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

        Button(
            onClick = onClickStop,
            modifier = Modifier.weight(1f),
            enabled = currentDistance > 0
        ) {
            Text("End Trip")
        }
    }
}
