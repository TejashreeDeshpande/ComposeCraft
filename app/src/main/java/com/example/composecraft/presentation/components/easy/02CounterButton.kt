package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CounterButton(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Counter = $counter")
        Button(onClick = {
            counter += 1
        }) {
            Text(text = "Increment")
        }
    }
}

@Preview
@Composable
fun CounterButtonPreview() {
    CounterButton()
}