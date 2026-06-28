package com.example.composecraft.features.fittrack.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewFTLabelSwitch() {
    FTLabelSwitch(
        title = "Track Weight",
        desc = "Allow weight to be logged",
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}

@Composable
fun FTLabelSwitch(
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    FTCard {
        Row(modifier = modifier) {
            Column(modifier = Modifier.weight(1f)) {
                FTTitle(title)
                Text(desc)
            }
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}

