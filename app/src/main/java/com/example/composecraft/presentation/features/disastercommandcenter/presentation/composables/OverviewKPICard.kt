package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiType
import com.example.composecraft.ui.theme.DisasterTheme

@Preview
@Composable
fun PreviewOverviewKPICard() {
    DisasterTheme {
        OverviewKPICard(
            KpiModel(
                type = KpiType.INCIDENTS,
                title = "Incidents",
                value = "23",
                trend = "+8",
                trendSuffix = "today"
            )
        )
    }
}

@Composable
fun OverviewKPICard(
    model: KpiModel,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        ),
    ) {
        Column(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            IncidentIcon(model.type.iconSpec)
            Text(
                text = model.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = model.title,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = model.trend,
                    color = model.type.iconSpec.color,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = model.trendSuffix,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}