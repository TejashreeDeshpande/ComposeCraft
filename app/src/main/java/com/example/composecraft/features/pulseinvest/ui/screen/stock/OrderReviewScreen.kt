package com.example.composecraft.features.pulseinvest.ui.screen.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockEffect
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun OrderReviewScreen(vm: StockViewModel, onBack: () -> Unit, onOrderPlaced: () -> Unit) {
    val state by vm.orderState.collectAsState()
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }

    LaunchedEffect(state.placedOrder) { if (state.placedOrder != null) onOrderPlaced() }

    LaunchedEffect(Unit) {
        vm.effect.collect { effect ->
            when (effect) {
                is StockEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
                StockEffect.NavigateToPortfolio -> { /* Handle navigation if needed */ }
            }
        }
    }

    PulseScaffold(
        title = "Review Order",
        showBack = true,
        onBack = onBack,
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(24.dp)) {
            // Stock card
            Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PulseSurface).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) { Text("🟢", fontSize = 28.sp) }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(state.stock?.name ?: "", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Buy ${state.shares} Shares", color = PulseTextSecondary, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            PulseCard {
                listOf("Order Type" to "Market Order", "Shares" to "${state.shares}",
                       "Estimated Cost" to "${"$%.2f".format(state.estimatedCost)}", "Fees" to "$0.00",
                       "Total" to "${"$%.2f".format(state.estimatedCost)}")
                    .forEachIndexed { i, (label, value) ->
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(label, color = PulseTextSecondary, fontSize = 14.sp)
                            Text(value, color = if (label == "Total") PulseGreen else PulseTextPrimary,
                                fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                        if (i < 4) androidx.compose.material3.HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
                    }
            }
            Spacer(Modifier.height(16.dp))
            Text("Confirm with PIN", color = PulseTextSecondary, fontSize = 14.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(Modifier.height(16.dp))
            NumPad(onDigit = {}, onBackspace = {})
            Spacer(Modifier.weight(1f))
            PulseButton("Place Order", onClick = vm::placeOrder, isLoading = state.isLoading)
            Spacer(Modifier.height(8.dp))
        }
    }
}
