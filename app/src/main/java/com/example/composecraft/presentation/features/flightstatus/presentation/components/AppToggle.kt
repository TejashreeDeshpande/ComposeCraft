package com.example.composecraft.presentation.features.flightstatus.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FlightStatusTheme

@Preview(showBackground = true)
@Composable
fun PreviewAppToggle() {
    FlightStatusTheme {
        AppToggle(
            modifier = Modifier.fillMaxWidth(),
            checked = false,
            onCheckedChange = {},
            leading = { AppBody("Toggle Label Toggle Label Toggle Label Toggle Label Toggle Label") }
        )
    }
}

@Composable
fun AppToggle(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    leading: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.weight(1f)) {
            leading()
        }

        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange(it) })
    }
}