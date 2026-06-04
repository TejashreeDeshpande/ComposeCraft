package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val numberFlow: Flow<Int> = flow {
    for (i in 1..10) {
        emit(i)
        delay(200)
    }
}

@Composable
fun DisplayFLowValue(modifier: Modifier = Modifier) {
    val currentNumber by numberFlow.collectAsState(initial = 1)
    Text(
        text = "Current Value: $currentNumber",
        modifier = modifier
    )
}

@Preview
@Composable
fun DisplayFlowValuePreview() {
    DisplayFLowValue(modifier = Modifier.padding(16.dp))
}
