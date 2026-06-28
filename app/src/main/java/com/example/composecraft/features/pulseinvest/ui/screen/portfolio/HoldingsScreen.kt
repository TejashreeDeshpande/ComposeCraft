package com.example.composecraft.features.pulseinvest.ui.screen.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.PortfolioViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun HoldingsScreen(vm: PortfolioViewModel, onBack: () -> Unit, onStockClick: (String) -> Unit) {
    val state by vm.state.collectAsState()

    PulseScaffold(title = "Holdings", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Sort: ", color = PulseTextSecondary, fontSize = 13.sp)
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(PulseSurface).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text("Value (High to Low) ▾", color = PulseGreen, fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
            items(state.holdings) { holding ->
                Column(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) {
                                Text("🟢", fontSize = 20.sp)
                            }
                            Column {
                                Text(holding.stock.name, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("${holding.shares} shares", color = PulseTextSecondary, fontSize = 12.sp)
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("${"$%,.2f".format(holding.totalValue)}", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("${if (holding.isPositive) "+" else ""}${"%.2f".format(holding.gainPercent)}%",
                                color = if (holding.isPositive) PulseGreen else PulseRed, fontSize = 12.sp)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    // Progress bar
                    val ratio = (holding.totalValue / state.totalValue).toFloat().coerceIn(0f, 1f)
                    LinearProgressIndicator(
                        progress = { ratio },
                        modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                        color = if (holding.isPositive) PulseGreen else PulseRed,
                        trackColor = PulseSurface2
                    )
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
