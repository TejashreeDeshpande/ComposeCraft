package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AppButtonPreview() {
    AppButton(
        label = "Start",
        backgroundColor = Color.Red,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AppButton(
    label: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {},
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
    ) {
        Text(text = label,
            color = Color.Blue)
    }
}
