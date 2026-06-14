package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FitTrackTheme
import com.example.composecraft.ui.theme.FontSize

@Preview
@Composable
fun PreviewFTListRow() {
    FitTrackTheme {
        FTListRow(
            leadingIconStr = "\uD83D\uDD87",
            label = "Bench Press",
            desc = "3 sets x 8-12 reps",
            trailingIconStr = "x"
        )
    }
}

@Composable
fun FTListRow(
    leadingIconStr: String = "",
    label: String,
    desc: String,
    trailingIconStr: String = "",
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIconStr.isNotBlank()) {
                FTCircleIcon(
                    iconStr = leadingIconStr,
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    fontSize = FontSize.HEADER.value,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = desc,
                    fontSize = FontSize.MEDIUM.value
                )
            }

            if (trailingIconStr.isNotBlank()) {
                FTCircleIcon(
                    iconStr = trailingIconStr,
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
        }
    }
}