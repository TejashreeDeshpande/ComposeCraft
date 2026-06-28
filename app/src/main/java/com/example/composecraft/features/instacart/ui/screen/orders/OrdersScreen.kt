package com.example.composecraft.features.instacart.ui.screen.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Order
import com.example.composecraft.features.instacart.domain.model.OrderStatus
import com.example.composecraft.features.instacart.presentation.viewmodel.OrdersViewModel
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(onBack: () -> Unit) {
    val vm: OrdersViewModel = koinViewModel()
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) { vm.refresh() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Orders", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        containerColor = InstacartBg
    ) { padding ->
        if (state.orders.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Receipt, contentDescription = null, tint = InstacartDivider, modifier = Modifier.size(72.dp))
                Spacer(Modifier.height(16.dp))
                Text("No orders yet", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Your order history will appear here", fontSize = 14.sp, color = InstacartTextSecondary)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.orders) { order ->
                    OrderCard(order = order)
                }
            }
        }
    }
}

@Composable
private fun OrderCard(order: Order) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(order.id, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = InstacartTextPrimary)
                StatusBadge(status = order.status)
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${order.items.sumOf { it.quantity }} items", fontSize = 13.sp, color = InstacartTextSecondary)
                Text("$${String.format("%.2f", order.total)}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                order.items.take(5).forEach { item ->
                    Text(item.product.imageEmoji, fontSize = 22.sp)
                }
                if (order.items.size > 5) {
                    Box(
                        modifier = Modifier.size(28.dp).background(InstacartBg, RoundedCornerShape(4.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+${order.items.size - 5}", fontSize = 11.sp, color = InstacartTextSecondary, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(color = InstacartDivider)
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("📍 ${order.address.label}", fontSize = 12.sp, color = InstacartTextSecondary)
                    Text("🕐 ${order.deliverySlot.label}", fontSize = 12.sp, color = InstacartTextSecondary, modifier = Modifier.padding(top = 2.dp))
                }
                Text(order.placedAt, fontSize = 12.sp, color = InstacartTextSecondary)
            }
        }
    }
}

@Composable
private fun StatusBadge(status: OrderStatus) {
    val (bg, fg) = when (status) {
        OrderStatus.PLACED -> Pair(Color(0xFFE3F2FD), Color(0xFF1565C0))
        OrderStatus.BEING_SHOPPED -> Pair(Color(0xFFFFF3E0), Color(0xFFE65100))
        OrderStatus.OUT_FOR_DELIVERY -> Pair(Color(0xFFE8F5E9), InstacartGreenDark)
        OrderStatus.DELIVERED -> Pair(InstacartGreenLight, InstacartGreenDark)
    }
    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text("${status.emoji} ${status.label}", fontSize = 12.sp, color = fg, fontWeight = FontWeight.Medium)
    }
}
