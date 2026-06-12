package com.example.composecraft.presentation.features.disastercommandcenter.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.CommandCenterViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables.ActiveIncidentCard
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables.AppTopBar
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables.IncidentCategoryFilter
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables.OverviewKPICard
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables.TeamInfoCard
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.IncidentType
import kotlin.math.roundToInt

@Composable
fun DisasterDashboard(
    viewModel: CommandCenterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KpiCardsRow(state.kpis)
            IncidentTypeRow(
                selectedType = state.selectedIncidentType,
                onTypeSelected = viewModel::onIncidentTypeSelected
            )
            MapView()
            ActiveIncidentsRow(
                incidents = state.incidents,
                onIncidentSelected = viewModel::selectIncident
            )
            TeamsOnField(state.teams)
        }
    }
}

@Composable
private fun IncidentTypeRow(
    selectedType: IncidentType?,
    onTypeSelected: (IncidentType?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        )
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(IncidentType.entries) { type ->
                IncidentCategoryFilter(
                    type,
                    isSelected = type == (selectedType ?: IncidentType.ALL),
                    onClick = {
                        onTypeSelected(if (type == IncidentType.ALL) null else type)
                    }
                )
            }
        }
    }
}

@Composable
private fun KpiCardsRow(kpis: List<KpiModel>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        kpis.forEach { data ->
            OverviewKPICard(data)
        }
    }
}

@Composable
fun MapView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        )
    ) {

    }
}

@Composable
fun ActiveIncidentsRow(
    incidents: List<Incident>,
    onIncidentSelected: (Incident) -> Unit
) {
    val listState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val density = LocalDensity.current

    val continuousIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (visibleItemsInfo.isEmpty()) {
                0f
            } else {
                val totalItems = incidents.size
                val itemSize = visibleItemsInfo.first().size.toFloat()
                val spacing = with(density) { 16.dp.toPx() }
                val padding = with(density) { 16.dp.toPx() }

                val currentScroll =
                    listState.firstVisibleItemIndex * (itemSize + spacing) + listState.firstVisibleItemScrollOffset
                val viewportWidth = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
                val totalContentWidth =
                    (totalItems * itemSize) + ((totalItems - 1) * spacing) + (padding * 2)
                val maxScroll = totalContentWidth - viewportWidth

                if (maxScroll <= 0) 0f
                else (currentScroll / maxScroll).coerceIn(0f, 1f) * (totalItems - 1)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowHeader("ACTIVE INCIDENTS")
        LazyRow(
            state = listState,
            flingBehavior = snapFlingBehavior,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(incidents) { incident ->
                ActiveIncidentCard(
                    incident = incident,
                    onClick = { onIncidentSelected(incident) }
                )
            }
        }

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val dotCount = incidents.size.coerceAtMost(3)
            val spacing = 8.dp
            val dotSize = 8.dp

            val targetDotIndex by remember {
                derivedStateOf {
                    val totalItems = incidents.size
                    val result = if (totalItems <= 3) {
                        continuousIndex
                    } else {
                        if (continuousIndex <= 1f) {
                            continuousIndex
                        } else if (continuousIndex >= totalItems - 2) {
                            1f + (continuousIndex - (totalItems - 2))
                        } else {
                            1f
                        }
                    }
                    result.coerceIn(0f, (dotCount - 1).coerceAtLeast(0).toFloat())
                }
            }

            Box(contentAlignment = Alignment.CenterStart) {
                Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                    repeat(dotCount) {
                        Box(
                            modifier = Modifier
                                .size(dotSize)
                                .background(Color.LightGray, CircleShape)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = ((dotSize + spacing).toPx() * targetDotIndex).roundToInt(),
                                y = 0
                            )
                        }
                        .size(dotSize)
                        .background(Color.DarkGray, CircleShape)
                )
            }
        }
    }
}

@Composable
fun RowHeader(
    title: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        TextButton(onClick = {}) {
            Text("View All")
        }
    }
}

@Composable
fun TeamsOnField(teams: List<Team>) {
    Column {
        RowHeader("TEAM ON FIELD")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(teams) { team ->
                TeamInfoCard(team)
            }
        }
    }

}
