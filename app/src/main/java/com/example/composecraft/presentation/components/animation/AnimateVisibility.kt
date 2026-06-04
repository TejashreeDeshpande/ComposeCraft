package com.example.composecraft.presentation.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimateVisibility(modifier: Modifier = Modifier) {
    var visibility by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { visibility = !visibility }) {
            Text("Toggle")
        }

        AnimatedVisibility(visible = visibility) {
            Text(
                text = "Hello Compose Animation",
                fontSize = 22.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}