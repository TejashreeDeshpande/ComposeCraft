package com.example.composecraft.features.fittrack.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.features.disastercommandcenter.data.model.FocusArea
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewFTSingleSelectableChipGroup() {
    FitTrackTheme {

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                FTSingleSelectableChipGroup(
                    chipItems = FocusArea.entries.map { it.title },
                    selectedChip = FocusArea.ALL.title,
                    onItemSelected = {},
                    colors = FTFilterChipColors.secondary()
                )
            }
        }
    }
}


@Composable
fun FTSingleSelectableChipGroup(
    chipItems: List<String>,
    selectedChip: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    colors: SelectableChipColors = FTFilterChipColors.primary()
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chipItems.forEach { item ->
            val isSelected = selectedChip == item

            FilterChip(
                selected = isSelected,
                onClick = { onItemSelected(item) },
                label = { Text(item) },
                shape = shape,
                colors = colors,
                elevation = FilterChipDefaults.filterChipElevation(elevation = 8.dp)
            )
        }
    }

}