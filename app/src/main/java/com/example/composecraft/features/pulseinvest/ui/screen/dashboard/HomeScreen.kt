package com.example.composecraft.features.pulseinvest.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.HomeViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onStockClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    val state by vm.state.collectAsState()
    val timeRanges = listOf("1D", "1W", "1M", "3M", "1Y", "ALL")

    Scaffold(containerColor = PulseDark) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = PulseGreen)
            }
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // ── Header ─────────────────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Good Morning 👋", fontSize = 14.sp, color = PulseTextSecondary)
                        Text(state.userName, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = PulseTextPrimary)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        IconBox("🔍", onClick = onSearchClick)
                        IconBox("🔔", onClick = onNotificationsClick)
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    "${"$%,.2f".format(state.portfolioValue)}",
                    fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary
                )
                ChangeBadge(state.todayChange, state.todayChangePercent)
                Spacer(Modifier.height(16.dp))
            }
            // ── Time Range + Sparkline ──────────────────────────────────────
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(timeRanges) { t ->
                        TimeChip(t, t == state.selectedTimeRange) { vm.onTimeRangeSelected(t) }
                    }
                }
                Spacer(Modifier.height(12.dp))
                SparklineChart(
                    data = state.chartData,
                    isPositive = state.todayChange >= 0,
                    modifier = Modifier
                        .fillMaxWidth().height(80.dp)
                        .clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(8.dp)
                )
                Spacer(Modifier.height(8.dp))
            }
            // ── Watchlist ───────────────────────────────────────────────────
            item { SectionHeader("Watchlist", "See All") {} }
            items(state.watchlist.take(4)) { stock ->
                StockRow(
                    emoji = "🟢",
                    name = stock.symbol,
                    subtitle = stock.name,
                    price = "${"$%.2f".format(stock.price)}",
                    change = "${if (stock.isPositive) "+" else ""}${"%.2f".format(stock.changePercent)}%",
                    isPositive = stock.isPositive,
                    onClick = { onStockClick(stock.symbol) }
                )
            }
            // ── Trending ────────────────────────────────────────────────────
            item { SectionHeader("Trending") }
            items(state.trending) { item ->
                Text(
                    item, color = PulseTextSecondary, fontSize = 13.sp,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                )
                HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun IconBox(icon: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp).clip(RoundedCornerShape(10.dp))
            .background(PulseSurface).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) { Text(icon, fontSize = 18.sp) }
}

@Composable
fun TimeChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) PulseGreen else PulseSurface)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            color = if (selected) PulseDark else PulseTextSecondary,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}
