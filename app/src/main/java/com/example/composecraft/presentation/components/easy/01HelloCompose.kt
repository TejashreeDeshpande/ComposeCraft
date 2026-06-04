package com.example.composecraft.presentation.components.easy

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HelloCompose(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        "Hello $name",
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Preview
@Composable
fun HelloComposePreview() {
    HelloCompose("Tejashree")
}