package com.example.composecraft.features.pulseinvest.ui.screen.stock

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.components.PulseOutlineButton
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun OrderSuccessScreen(vm: StockViewModel, onViewPortfolio: () -> Unit, onBackToHome: () -> Unit) {
    val state by vm.orderState.collectAsState()
    val order = state.placedOrder

    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(modifier = Modifier.fillMaxSize().padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.size(100.dp).clip(CircleShape).background(PulseGreen), contentAlignment = Alignment.Center) {
                Text("✓", fontSize = 48.sp, color = Color.Black, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.height(24.dp))
            Text("Order Placed Successfully!", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("You bought ${order?.shares ?: state.shares} shares of ${order?.stock?.name ?: ""}", fontSize = 16.sp, color = PulseTextSecondary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("Total Amount: ${"$%.2f".format(order?.total ?: state.estimatedCost)}", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = PulseGreen)
            Spacer(Modifier.height(24.dp))
            Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Order ID", color = PulseTextSecondary, fontSize = 13.sp)
                    Text(order?.id ?: "#PLS-0001", color = PulseTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
                HorizontalDivider(color = PulseSurface2)
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Status", color = PulseTextSecondary, fontSize = 13.sp)
                    Text("Executed ✓", color = PulseGreen, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(28.dp))
            PulseButton("View Portfolio", onClick = onViewPortfolio)
            Spacer(Modifier.height(12.dp))
            PulseOutlineButton("Back to Home", onClick = onBackToHome)
        }
    }
}
