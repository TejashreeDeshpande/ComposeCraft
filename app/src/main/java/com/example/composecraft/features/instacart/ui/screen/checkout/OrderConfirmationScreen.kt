package com.example.composecraft.features.instacart.ui.screen.checkout

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Order
import com.example.composecraft.features.instacart.domain.model.OrderStatus
import com.example.composecraft.features.instacart.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(order: Order, onContinueShopping: () -> Unit, onViewOrders: () -> Unit) {
    val scale by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 1f, targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
        label = "scale"
    )

    Scaffold(containerColor = InstacartBg) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .scale(scale)
                        .background(InstacartGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(52.dp))
                }
                Spacer(Modifier.height(16.dp))
                Text("Order Placed!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = InstacartGreenDark)
                Spacer(Modifier.height(4.dp))
                Text(
                    "Your groceries are being prepared",
                    fontSize = 15.sp,
                    color = InstacartTextSecondary,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = InstacartSurface),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Order ID", fontSize = 13.sp, color = InstacartTextSecondary)
                            Text(order.id, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Total", fontSize = 13.sp, color = InstacartTextSecondary)
                            Text("$${String.format("%.2f", order.total)}", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Delivery to", fontSize = 13.sp, color = InstacartTextSecondary)
                            Text("${order.address.label} – ${order.address.street}", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Delivery window", fontSize = 13.sp, color = InstacartTextSecondary)
                            Text(order.deliverySlot.label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            item {
                Text("Order Progress", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                OrderProgressTracker(currentStatus = order.status)
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("📱", fontSize = 24.sp)
                        Column {
                            Text("Track your order", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            Text("You'll receive SMS updates on delivery status", fontSize = 12.sp, color = InstacartTextSecondary)
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = onViewOrders,
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen)
                    ) {
                        Text("View My Orders", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = onContinueShopping,
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = InstacartGreen),
                        border = ButtonDefaults.outlinedButtonBorder.copy()
                    ) {
                        Text("Continue Shopping", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderProgressTracker(currentStatus: OrderStatus) {
    val steps = OrderStatus.values()
    val currentIndex = steps.indexOf(currentStatus)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            steps.forEachIndexed { index, status ->
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                if (index <= currentIndex) InstacartGreen else InstacartDivider,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(status.emoji, fontSize = 16.sp)
                    }
                    Column {
                        Text(
                            status.label,
                            fontSize = 14.sp,
                            fontWeight = if (index == currentIndex) FontWeight.Bold else FontWeight.Normal,
                            color = if (index <= currentIndex) InstacartTextPrimary else InstacartTextSecondary
                        )
                        if (index == currentIndex) {
                            Text("In progress...", fontSize = 12.sp, color = InstacartGreen)
                        }
                    }
                    if (index <= currentIndex) {
                        Spacer(Modifier.weight(1f))
                        if (index < currentIndex) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}
