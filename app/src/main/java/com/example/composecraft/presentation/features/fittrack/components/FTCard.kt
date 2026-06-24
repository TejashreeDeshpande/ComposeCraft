package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FitTrackTheme
import com.example.composecraft.ui.theme.LocalAppSpacing

@Preview
@Composable
fun PreviewFTCard() {
    FitTrackTheme {
        FTCard(
            modifier = Modifier.padding(16.dp),
            content = {
                Text("Test Card")
            }
        )
    }
}

@Composable
fun FTCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: CardColors = FTCardColors.disabled(),
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = LocalAppSpacing.current

    if (onClick != null) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            colors = colors
        ) {
            Column(
                Modifier.padding(spacing.md),
                content = content
            )
        }
    } else {
        ElevatedCard(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                Modifier.padding(spacing.xxs),
                content = content
            )
        }
    }
}

//val borderWidth = 4.dp
//val borderColor = Color.Blue
//val cardRadius = 12.dp
//.drawLeftCurvedBorder(
//borderWidth = borderWidth,
//borderColor = borderColor,
//cardRadius = cardRadius
//),

//@Preview
//@Composable
//fun RedTitleCard() {
//    FTCard {
//        Column {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Red)
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "Card Title",
//                    color = Color.White // White text for visibility
//                )
//            }
//
//            // Body
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = "This is the body text of the card. You can add more information or actions here.")
//            }
//        }
//    }
//}
