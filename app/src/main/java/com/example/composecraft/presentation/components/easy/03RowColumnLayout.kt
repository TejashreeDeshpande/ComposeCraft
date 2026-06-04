package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Row and Column layout
//Arrange three colored boxes — one on top, two side-by-side below — using only Row and Column. No Box or other containers.
@Composable
fun RowColumnLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)) {
            drawRect(Color.Red)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Canvas(modifier = Modifier
                .height(100.dp)
                .weight(1f)) {
                drawRect(Color.Green)
            }
            Canvas(modifier = Modifier
                .height(100.dp)
                .weight(1f)) {
                drawRect(Color.Blue)
            }
        }
    }
}

@Preview
@Composable
fun RowColumnLayoutPreview() {
    RowColumnLayout()
}
