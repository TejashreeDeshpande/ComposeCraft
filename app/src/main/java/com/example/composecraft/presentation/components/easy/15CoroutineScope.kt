package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CoroutineScope(modifier: Modifier = Modifier) {
    var text1 by remember { mutableStateOf("Waiting...") }
    var text2 by remember { mutableStateOf("Waiting...") }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = {
            scope.launch {
                delay(1000)
                text1 = "Updated after 1s"
            }
            scope.launch {
                delay(2000)
                text2 = "Updated after 2s"
            }
        }) {
            Text(text = "Start")
        }
        Text(text1)
        Text(text2)
    }
}