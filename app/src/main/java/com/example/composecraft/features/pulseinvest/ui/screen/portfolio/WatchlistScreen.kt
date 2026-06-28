package com.example.composecraft.features.pulseinvest.ui.screen.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.PortfolioViewModel
import com.example.composecraft.features.pulseinvest.ui.components.SparklineChart
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun WatchlistScreen(vm: PortfolioViewModel, onStockClick: (String) -> Unit) {
    val state by vm.state.collectAsState()

    Scaffold(containerColor = PulseDark) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Watchlist", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary)
                    Box(
                        modifier = Modifier.size(36.dp).clip(CircleShape).background(PulseGreen),
                        contentAlignment = Alignment.Center
                    ) { Text("+", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold) }
                }
                Spacer(Modifier.height(14.dp))
            }
            items(state.watchlist) { stock ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(PulseSurface2),
                        contentAlignment = Alignment.Center
                    ) { Text("🟢", fontSize = 18.sp) }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 10.dp)) {
                        Text(stock.symbol, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Text(stock.name, color = PulseTextSecondary, fontSize = 12.sp)
                    }
                    SparklineChart(
                        stock.sparkline, stock.isPositive,
                        Modifier.width(60.dp).height(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Column(horizontalAlignment = Alignment.End) {
                        Text("${"$%.2f".format(stock.price)}", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            "${if (stock.isPositive) "+" else ""}${"%.2f".format(stock.changePercent)}%",
                            color = if (stock.isPositive) PulseGreen else PulseRed, fontSize = 12.sp
                        )
                    }
                }
                HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
