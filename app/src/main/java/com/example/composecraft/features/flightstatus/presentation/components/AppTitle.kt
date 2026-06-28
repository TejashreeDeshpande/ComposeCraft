package com.example.composecraft.features.flightstatus.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppTitle(
    text: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontWeight: FontWeight = FontWeight.Normal
) {
    if (icon != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon.invoke()
            Text(
                text = text,
                modifier = modifier,
                style = style,
                color = color,
                fontWeight = fontWeight,
            )
        }

    } else {
        Text(
            text = text,
            modifier = modifier,
            style = style,
            color = color,
            fontWeight = fontWeight,
        )
    }
}

@Composable
fun AppCaption(
    text: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    AppTitle(
        text = text,
        icon = icon,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        color = color
    )
}

@Composable
fun AppBody(
    text: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    AppTitle(
        text = text,
        icon = icon,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = color
    )
}

@Composable
fun AppBodyBold(
    text: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    AppTitle(
        text = text,
        icon = icon,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = color
    )
}

@Composable
fun AppErrorText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.error
    )
}