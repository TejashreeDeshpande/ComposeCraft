package com.example.composecraft.features.pulseinvest.ui.screen.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.composecraft.features.pulseinvest.domain.model.OrderSide
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun BuySellScreen(symbol: String, vm: StockViewModel, onBack: () -> Unit, onReviewOrder: () -> Unit) {
    LaunchedEffect(symbol) { vm.loadStock(symbol) }
    val state by vm.orderState.collectAsState()
    val stock = state.stock

    PulseScaffold(title = stock?.symbol ?: symbol, showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 24.dp)) {
            Spacer(Modifier.height(8.dp))
            PulseTabRow(listOf("Buy","Sell"), if (state.side == OrderSide.BUY) "Buy" else "Sell") { t ->
                vm.onSideChange(if (t == "Buy") OrderSide.BUY else OrderSide.SELL)
            }
            Spacer(Modifier.height(16.dp))
            // Stock info row
            Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) {
                    Text("🟢", fontSize = 28.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(stock?.name ?: "", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("${"$%.2f".format(stock?.price ?: 0.0)} · ${stock?.symbol ?: ""}", color = PulseTextSecondary, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(20.dp))
            FieldRow("Available to Invest", "${"$,.2f".format(state.availableBalance)}")
            FieldRow("Order Type", "Market Order")
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Shares", color = PulseTextSecondary, fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(PulseSurface2).clickable { vm.decrementShares() }, contentAlignment = Alignment.Center) {
                        Text("−", color = PulseTextPrimary, fontSize = 20.sp)
                    }
                    Text("${state.shares}", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(PulseSurface2).clickable { vm.incrementShares() }, contentAlignment = Alignment.Center) {
                        Text("+", color = PulseTextPrimary, fontSize = 20.sp)
                    }
                }
            }
            HorizontalDivider(color = PulseSurface2, modifier = Modifier.padding(vertical = 12.dp))
            FieldRow("Estimated Cost", "${"$%.2f".format(state.estimatedCost)}")
            FieldRow("Fees", "$0.00")
            HorizontalDivider(color = PulseSurface2, modifier = Modifier.padding(vertical = 12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", color = PulseTextSecondary, fontSize = 15.sp)
                Text("${"$%.2f".format(state.estimatedCost)}", color = PulseGreen, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.weight(1f))
            PulseButton("Review Order", onClick = onReviewOrder)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun FieldRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = PulseTextSecondary, fontSize = 14.sp)
        Text(value, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}
