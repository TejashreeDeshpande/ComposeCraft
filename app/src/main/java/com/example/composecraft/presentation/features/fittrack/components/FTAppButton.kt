package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
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
        modifier = Modifier.fillMaxWidth(),
        colors = FTAppButtonColors.primary()
    )
}

@Composable
fun FTAppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    colors: ButtonColors = FTAppButtonColors.primary(),
) {
    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = colors
    ) {
        leadingIcon?.invoke()
        if (leadingIcon != null) Spacer(Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.BODY.value
        )
    }
}