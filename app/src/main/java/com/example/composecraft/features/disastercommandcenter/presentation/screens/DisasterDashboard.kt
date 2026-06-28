package com.example.composecraft.features.disastercommandcenter.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composecraft.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.features.disastercommandcenter.data.model.IncidentType
import com.example.composecraft.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.features.disastercommandcenter.data.model.Team
import com.example.composecraft.features.disastercommandcenter.presentation.CommandCenterViewModel
import com.example.composecraft.features.disastercommandcenter.presentation.composables.ActiveIncidentCard
import com.example.composecraft.features.disastercommandcenter.presentation.composables.AppTopBar
import com.example.composecraft.features.disastercommandcenter.presentation.composables.IncidentCategoryFilter
import com.example.composecraft.features.disastercommandcenter.presentation.composables.MockMapView
import com.example.composecraft.features.disastercommandcenter.presentation.composables.OverviewKPICard
import com.example.composecraft.features.disastercommandcenter.presentation.composables.TeamInfoCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun DisasterDashboard(
    viewModel: CommandCenterViewModel = koinViewModel(),
    onTeamSelected: (Team) -> Unit,
    onBack: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(onBack = onBack)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage ?: "Unknown Error",
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                AnimatedVisibility(
                    visible = !state.isLoading,
                    enter = fadeIn() + slideInVertically { it / 2 }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
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
                        TeamsOnField(
                            teams = state.teams,
                            onTeamSelected = onTeamSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun IncidentTypeRow(
    selectedType: IncidentType?,
    onTypeSelected: (IncidentType?) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(IncidentType.entries, key = { it.name }) { type ->
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

@Composable
private fun KpiCardsRow(kpis: List<KpiModel>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        kpis.forEach { data ->
            OverviewKPICard(
                model = data,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun MapView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MockMapView(modifier = Modifier.fillMaxSize())
            
            // Map Overlay Badge
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF22C55E), CircleShape)
                    )
                    Text(
                        "LIVE SYSTEM",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun ActiveIncidentsRow(
    incidents: List<Incident>,
    onIncidentSelected: (Incident) -> Unit
) {
    if (incidents.isEmpty()) return

    val listState = rememberLazyListState()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RowHeader("ACTIVE INCIDENTS")

        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(incidents, key = { it.title }) { incident ->
                ActiveIncidentCard(
                    incident = incident,
                    modifier = Modifier.fillMaxHeight(),
                    onClick = { onIncidentSelected(incident) }
                )
            }
        }

        HorizontalScrollbar(
            state = listState,
            modifier = Modifier
                .padding(top = 4.dp)
                .width(48.dp)
        )
    }
}

@Composable
fun HorizontalScrollbar(
    state: LazyListState,
    modifier: Modifier = Modifier
) {
    val scrollbarInfo by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount
            val visibleItemsCount = layoutInfo.visibleItemsInfo.size
            if (totalItemsCount <= visibleItemsCount || totalItemsCount == 0) {
                null
            } else {
                val fraction = state.firstVisibleItemIndex.toFloat() / (totalItemsCount - visibleItemsCount)
                val widthFraction = visibleItemsCount.toFloat() / totalItemsCount
                Pair(fraction, widthFraction)
            }
        }
    }

    scrollbarInfo?.let { (fraction, widthFraction) ->
        Box(
            modifier = modifier
                .height(4.dp)
                .background(
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    CircleShape
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(widthFraction)
                    .fillMaxHeight()
                    .align(
                        BiasAlignment(
                            horizontalBias = fraction * 2f - 1f,
                            verticalBias = 0f
                        )
                    )
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
    }
}

@Composable
fun RowHeader(
    title: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = 0.5.sp
            )
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(3.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
        TextButton(
            onClick = {},
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                "View All",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TeamsOnField(
    teams: List<Team>,
    onTeamSelected: (Team) -> Unit
) {
    val listState = rememberLazyListState()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RowHeader("TEAM ON FIELD")
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(teams, key = { it.name }) { team ->
                TeamInfoCard(
                    team = team,
                    modifier = Modifier.fillMaxHeight(),
                    onClick = { onTeamSelected(team) }
                )
            }
        }

        HorizontalScrollbar(
            state = listState,
            modifier = Modifier
                .padding(top = 4.dp)
                .width(48.dp)
        )
    }
}
