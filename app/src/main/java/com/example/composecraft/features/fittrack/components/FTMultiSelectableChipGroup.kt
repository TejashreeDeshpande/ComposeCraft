package com.example.composecraft.features.fittrack.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.features.disastercommandcenter.data.model.Equipment
import com.example.composecraft.features.disastercommandcenter.data.model.ExerciseType
import com.example.composecraft.ui.theme.FitTrackTheme

@Preview
@Composable
fun PreviewFTMultiSelectableChipGroup() {
    FitTrackTheme {

        val selectedEquipmentChips = remember { mutableStateListOf<String>() }
        val selectedExerciseTypeChips = remember { mutableStateListOf<String>() }

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Equipments
                FTTitle("Equipment")
                FTMultiSelectableChipGroup(
                    chipItems = Equipment.entries.map { it.title },
                    selectedChips = selectedEquipmentChips,
                    onItemSelected = {
                        if (it in selectedEquipmentChips)
                            selectedEquipmentChips.remove(it)
                        else
                            selectedEquipmentChips.add(it)
                    }
                )
                // Exercises
                FTTitle("Exercise Type")
                FTMultiSelectableChipGroup(
                    chipItems = ExerciseType.entries.map { it.title },
                    selectedChips = selectedExerciseTypeChips,
                    onItemSelected = {
                        if (it in selectedExerciseTypeChips)
                            selectedEquipmentChips.remove(it)
                        else
                            selectedEquipmentChips.add(it)
                    }
                )
            }
        }
    }
}


@Composable
fun FTMultiSelectableChipGroup(
    chipItems: List<String>,
    selectedChips: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chipItems.forEach { item ->
            val isSelected = item in selectedChips

            FilterChip(
                selected = isSelected,
                onClick = { onItemSelected(item) },
                label = { Text(item) },
                shape = RoundedCornerShape(24.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = MaterialTheme.colorScheme.onSurface
                ),
                elevation = FilterChipDefaults.filterChipElevation(
                    elevation = 8.dp
                )
            )
        }
    }

}