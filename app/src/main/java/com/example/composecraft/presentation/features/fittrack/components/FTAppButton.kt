package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FontSize

@Preview
@Composable
fun FTAppButtonPreview() {
    FTAppButton(
        text = "Start",
        onClick = {},
        type = ButtonType.Primary,
        modifier = Modifier.fillMaxWidth()
    )
}

enum class ButtonType {
    Primary,
    Secondary,
    Tertiary
}

@Composable
fun FTAppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.Primary
) {
    ElevatedButton(
        onClick = {
            onClick()
        },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors =
            when (type) {
                ButtonType.Primary -> {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                }

                ButtonType.Secondary -> {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                }

                ButtonType.Tertiary -> {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.BODY.value
        )
    }
}