package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun PreviewFTTextField() {
    FTTextField(
        title = "Workout Name",
        value = "",
        onValueChanged = {})
}

@Composable
fun FTTextField(
    title: String,
    value: String,
    onValueChanged: () -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged() },
        modifier = Modifier.fillMaxWidth()
    )
}