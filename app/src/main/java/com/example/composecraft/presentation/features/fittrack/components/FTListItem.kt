package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewFTListItem() {
    FitTrackTheme {
        FTListItem(
            title = "Bench Press",
            subTitle = "3 sets x 8-12 reps",
            leading = {
                FTCircleIcon(
                    iconStr = "T",
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            },
            trailing = {
                FTCircleIcon(
                    iconStr = "T",
                    iconSize = 44.dp,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickActionButton = {}
                )
            }
        )
    }
}

@Composable
fun FTListItem(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (onClick != null) Modifier.clickable { onClick() } else Modifier
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leading?.let {
                it()
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),

                ) {
                FTTitle(title)
                FTCaption(subTitle)
            }
            trailing?.invoke()
        }
    }
}