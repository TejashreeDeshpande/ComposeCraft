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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.domain.model.AllocationSlice
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.PortfolioViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

/** Parse a 6-digit hex string like "#00FF88" to Compose Color. */
private fun hexColor(hex: String): Color {
    val clean = hex.trimStart('#')
    return try {
        val r = clean.substring(0, 2).toInt(16)
        val g = clean.substring(2, 4).toInt(16)
        val b = clean.substring(4, 6).toInt(16)
        Color(r, g, b)
    } catch (_: Exception) {
        PulseGreen
    }
}

@Composable
fun PortfolioScreen(
    vm: PortfolioViewModel,
    onStockClick: (String) -> Unit,
    onViewHoldings: () -> Unit
) {
    val state by vm.state.collectAsState()

    Scaffold(containerColor = PulseDark) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)
        ) {
            // ── Header ────────────────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                Text("Portfolio", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary)
                Text("Total Value", color = PulseTextSecondary, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                Text(
                    "${"$%,.2f".format(state.totalValue)}",
                    fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary
                )
                ChangeBadge(state.todayChange, state.todayChangePercent)
                Spacer(Modifier.height(16.dp))
                PulseTabRow(
                    listOf("Holdings", "Allocation", "Performance"),
                    state.selectedTab
                ) { vm.onTabSelected(it) }
                Spacer(Modifier.height(16.dp))
            }

            // ── Allocation tab ─────────────────────────────────────────────
            if (state.selectedTab == "Allocation") {
                item {
                    AllocationView(state.allocationData)
                    Spacer(Modifier.height(16.dp))
                }
            }

            // ── Holdings / Performance tab ──────────────────────────────────
            if (state.selectedTab != "Allocation") {
                items(state.holdings) { holding ->
                    StockRow(
                        emoji = "🟢",
                        name = holding.stock.symbol,
                        subtitle = "${holding.shares} shares",
                        price = "${"$%,.2f".format(holding.totalValue)}",
                        change = "${if (holding.isPositive) "+" else ""}${"%.2f".format(holding.gainPercent)}%",
                        isPositive = holding.isPositive,
                        onClick = { onStockClick(holding.stock.symbol) }
                    )
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun AllocationView(slices: List<AllocationSlice>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Simple visual donut placeholder
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(55.dp))
                .background(PulseSurface),
            contentAlignment = Alignment.Center
        ) {
            Text("$124K", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            slices.forEach { slice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(hexColor(slice.colorHex))
                    )
                    Text(
                        "${slice.label}  ${(slice.percent * 100).toInt()}%",
                        color = PulseTextSecondary, fontSize = 12.sp
                    )
                }
            }
        }
    }
}
