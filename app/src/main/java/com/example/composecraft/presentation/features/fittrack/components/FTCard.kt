package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.fittrack.utils.drawLeftCurvedBorder
import kotlin.io.path.Path

@Composable
fun FTCard(
    modifier: Modifier = Modifier,
    colors: CardColors = FTCardColors.disabled(),
    content: @Composable () -> Unit,
) {
    val borderWidth = 4.dp
    val borderColor = Color.Blue
    val cardRadius = 12.dp

    Card(
        modifier = modifier
            .padding(16.dp)
            .drawLeftCurvedBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                cardRadius = cardRadius
            ),
        colors = colors,
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        content()
    }
}

@Preview
@Composable
fun RedTitleCard() {
    FTCard {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Card Title",
                    color = Color.White // White text for visibility
                )
            }

            // Body
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "This is the body text of the card. You can add more information or actions here.")
            }
        }
    }
}
