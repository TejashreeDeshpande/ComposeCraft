package com.example.composecraft.presentation.features.vehicle.common.tripprogress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripProgressScreen() {
    val viewModel: TripProgressViewModel = koinViewModel()

    val progress = viewModel.progress.collectAsStateWithLifecycle()

    val progressValue by animateFloatAsState(
        targetValue = progress.value,
        animationSpec = tween(),
        label = "ProgressAnimation"
    )

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LinearProgressIndicator(
                progress = { progressValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }

}
