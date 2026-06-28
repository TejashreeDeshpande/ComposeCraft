package com.example.composecraft.features.vehicle.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SelectTimeWidgetPreview(modifier: Modifier = Modifier) {
    SelectTimeWidget()
}

@Composable
fun SelectTimeWidget(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableIntStateOf(10) }
    Box(
        modifier = Modifier.padding(20.dp)
    ) {
        Column {
            StepperRow(
                counter = counter,
                onCounterUpdate = { deltaValue ->
                    counter = (counter + deltaValue).coerceAtLeast(0)
                })
            QuickSelectRow(
                counter = counter,
                onCounterUpdate = { newValue ->
                    counter = newValue
                })
        }
    }
}

@Composable
fun QuickSelectRow(
    counter: Int,
    onCounterUpdate: (Int) -> Unit
) {
    val quickList = listOf(5, 10, 15, 20)
    Row {
        quickList.forEach { item ->
            FilterChip(
                selected = counter == item,
                onClick = { onCounterUpdate(item) },
                label = { Text(text = "$item min")},
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}

@Composable
fun StepperRow(
    counter: Int,
    onCounterUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedIconButton(
            onClick = { onCounterUpdate(-1) },
            modifier = modifier
                .size(44.dp)
                .semantics { contentDescription = "Decrease minutes" },
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color.Gray),
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = counter.toString(),
                fontSize = 28.sp
            )
            Text(
                text = "minutes",
                fontSize = 12.sp
            )
        }
        OutlinedIconButton(
            onClick = { onCounterUpdate(+1) },
            modifier = modifier
                .size(44.dp)
                .semantics { contentDescription = "Increase Minutes" },
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }

}