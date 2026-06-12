package com.example.composecraft.presentation.features.vehicle.common.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
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
fun ProgressScreenPreview() {
    ProgressScreenContents(
        progress = 0.4f,
        onClickStart = {},
        onClickStop = {})
}

@Composable
fun ProgressScreen(
    viewModel: ProgressViewModel = koinViewModel()
) {
    val progress = viewModel.progress.collectAsStateWithLifecycle()
    ProgressScreenContents(
        progress.value,
        onClickStart = {
            viewModel.startTrip(56.0f)
        },
        onClickStop = {
            viewModel.stop()
        })
}

@Composable
fun ProgressScreenContents(
    progress: Float,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgress(progress)
            CircularProgress(progress)
            ActionsRow(
                onClickStart = {
                    onClickStart()
                },
                onClickStop = {
                    onClickStop()
                },
            )
        }
    }
}

@Composable
fun CircularProgress(progress: Float) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(),
        label = "Circular progress"
    )
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(200.dp)
        )
    }
    Text(
        text = "${(progress * 100).toInt()}%"
    )

}

@Composable
fun LinearProgress(progress: Float) {
    val progressAnim by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(),
        label = "progress"
    )

    LinearProgressIndicator(
        progress = { progressAnim },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun ActionsRow(
    onClickStart: () -> Unit,
    onClickStop: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onClickStart() }) { Text("start") }
        Button(onClick = { onClickStop() }) { Text("stop") }
    }
}