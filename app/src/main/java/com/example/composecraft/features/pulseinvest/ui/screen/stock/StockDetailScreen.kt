package com.example.composecraft.features.pulseinvest.ui.screen.stock

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.screen.dashboard.TimeChip
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun StockDetailScreen(
    symbol: String,
    vm: StockViewModel,
    onBack: () -> Unit,
    onBuy: () -> Unit
) {
    LaunchedEffect(symbol) { vm.loadStock(symbol) }
    val state by vm.detailState.collectAsState()
    val stock = state.stock
    val timeRanges = listOf("1D", "1W", "1M", "3M", "1Y", "5Y")

    PulseScaffold(title = stock?.symbol ?: symbol, showBack = true, onBack = onBack) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = PulseGreen)
            }
            return@PulseScaffold
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)
        ) {
            // ── Price header ──────────────────────────────────────────────
            item {
                Spacer(Modifier.height(4.dp))
                Text(stock?.name ?: "", color = PulseTextSecondary, fontSize = 13.sp)
                Text(
                    "${"$%.2f".format(stock?.price ?: 0.0)}",
                    fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary
                )
                stock?.let { ChangeBadge(it.change, it.changePercent) }
                Spacer(Modifier.height(12.dp))

                // Time range chips
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(timeRanges) { t ->
                        TimeChip(t, t == state.selectedTimeRange) { vm.onTimeRangeSelected(t) }
                    }
                }
                Spacer(Modifier.height(10.dp))

                // Sparkline
                SparklineChart(
                    data = stock?.sparkline ?: listOf(60f, 50f, 55f, 30f, 35f, 15f, 10f),
                    isPositive = stock?.isPositive ?: true,
                    modifier = Modifier
                        .fillMaxWidth().height(100.dp)
                        .clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(8.dp)
                )
                Spacer(Modifier.height(16.dp))
            }

            // ── About ─────────────────────────────────────────────────────
            item {
                Text("About", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(Modifier.height(6.dp))
                Text(stock?.about ?: "", color = PulseTextSecondary, fontSize = 13.sp, lineHeight = 20.sp)
                Spacer(Modifier.height(16.dp))
            }

            // ── Key Stats grid ────────────────────────────────────────────
            item {
                Text("Key Stats", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatBox("Market Cap",  stock?.marketCap ?: "--",   modifier = Modifier.weight(1f))
                    StatBox("P/E Ratio",   stock?.peRatio  ?: "--",   modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatBox("52W High", "${"$%.2f".format(stock?.high52w ?: 0.0)}", modifier = Modifier.weight(1f))
                    StatBox("52W Low",  "${"$%.2f".format(stock?.low52w  ?: 0.0)}", modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(20.dp))
            }

            // ── Buy / Sell buttons ─────────────────────────────────────────
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = onBuy,
                        modifier = Modifier.weight(1f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PulseGreen, contentColor = Color.Black),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Buy", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
                    Button(
                        onClick = onBuy,
                        modifier = Modifier.weight(1f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PulseRed, contentColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Sell", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(PulseSurface)
            .padding(12.dp)
    ) {
        Text(label, color = PulseTextSecondary, fontSize = 11.sp)
        Spacer(Modifier.height(3.dp))
        Text(value, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
