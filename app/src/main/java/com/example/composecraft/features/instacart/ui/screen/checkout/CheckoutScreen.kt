package com.example.composecraft.features.instacart.ui.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Address
import com.example.composecraft.features.instacart.domain.model.DeliverySlot
import com.example.composecraft.features.instacart.presentation.viewmodel.CartViewModel
import com.example.composecraft.features.instacart.presentation.viewmodel.CheckoutViewModel
import com.example.composecraft.features.instacart.ui.components.PriceRow
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(onBack: () -> Unit, onOrderPlaced: (String) -> Unit) {
    val vm: CheckoutViewModel = koinViewModel()
    val cartVm: CartViewModel = koinViewModel()
    val state by vm.state.collectAsState()
    val cartState by cartVm.state.collectAsState()

    LaunchedEffect(state.placedOrder) {
        state.placedOrder?.let { onOrderPlaced(it.id) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = InstacartSurface) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PriceRow("Total", "$${String.format("%.2f", cartState.cart.total)}", bold = true)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = vm::placeOrder,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen),
                        enabled = !state.isPlacingOrder && state.selectedAddress != null && state.selectedSlot != null
                    ) {
                        if (state.isPlacingOrder) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        } else {
                            Text("Place Order • $${String.format("%.2f", cartState.cart.total)}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        },
        containerColor = InstacartBg
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { SectionCard(title = "Delivery Address", icon = "📍") {
                state.addresses.forEach { address ->
                    AddressOption(
                        address = address,
                        isSelected = address.id == state.selectedAddress?.id,
                        onSelect = { vm.selectAddress(address) }
                    )
                }
            }}

            item { SectionCard(title = "Delivery Time", icon = "🕐") {
                state.deliverySlots.forEach { slot ->
                    SlotOption(
                        slot = slot,
                        isSelected = slot.id == state.selectedSlot?.id,
                        onSelect = { vm.selectSlot(slot) }
                    )
                }
            }}

            item { SectionCard(title = "Payment", icon = "💳") {
                PaymentMethod()
            }}

            item { SectionCard(title = "Order Summary", icon = "📋") {
                cartState.cart.items.forEach { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(item.product.imageEmoji, fontSize = 18.sp)
                            Text("${item.product.name} ×${item.quantity}", fontSize = 14.sp, color = InstacartTextPrimary)
                        }
                        Text(
                            "$${String.format("%.2f", item.product.price * item.quantity)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = InstacartDivider)
                PriceRow("Subtotal", "$${String.format("%.2f", cartState.cart.subtotal)}")
                PriceRow("Delivery", if (cartState.cart.deliveryFee == 0.0) "FREE" else "$${String.format("%.2f", cartState.cart.deliveryFee)}", color = if (cartState.cart.deliveryFee == 0.0) InstacartGreen else InstacartTextPrimary)
                PriceRow("Service fee", "$${String.format("%.2f", cartState.cart.serviceFee)}")
                if (cartState.cart.discount > 0) {
                    PriceRow("Promo", "-$${String.format("%.2f", cartState.cart.discount)}", color = InstacartGreen)
                }
            }}

            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun SectionCard(title: String, icon: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(icon, fontSize = 20.sp)
                Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun AddressOption(address: Address, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) InstacartGreenLight else Color.Transparent)
            .border(1.dp, if (isSelected) InstacartGreen else InstacartDivider, RoundedCornerShape(10.dp))
            .clickable { onSelect() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = InstacartGreen)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(address.label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if (isSelected) InstacartGreenDark else InstacartTextPrimary)
            Text("${address.street}, ${address.city}, ${address.state} ${address.zip}", fontSize = 13.sp, color = InstacartTextSecondary)
        }
        Icon(Icons.Default.Home, contentDescription = null, tint = if (isSelected) InstacartGreen else InstacartTextSecondary, modifier = Modifier.size(20.dp))
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun SlotOption(slot: DeliverySlot, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) InstacartGreenLight else Color.Transparent)
            .border(1.dp, if (isSelected) InstacartGreen else InstacartDivider, RoundedCornerShape(10.dp))
            .clickable { onSelect() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = InstacartGreen)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(slot.label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if (isSelected) InstacartGreenDark else InstacartTextPrimary)
            Text(slot.timeRange, fontSize = 13.sp, color = InstacartTextSecondary)
        }
        Text(
            if (slot.price == 0.0) "FREE" else "$${String.format("%.2f", slot.price)}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (slot.price == 0.0) InstacartGreen else InstacartTextPrimary
        )
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun PaymentMethod() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(InstacartGreenLight)
            .border(1.dp, InstacartGreen, RoundedCornerShape(10.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("💳", fontSize = 24.sp)
        Column(modifier = Modifier.weight(1f)) {
            Text("Visa •••• 4242", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("Expires 12/27", fontSize = 12.sp, color = InstacartTextSecondary)
        }
        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(20.dp))
    }
    Spacer(Modifier.height(8.dp))
    TextButton(onClick = {}) {
        Icon(Icons.Default.Add, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(4.dp))
        Text("Add payment method", color = InstacartGreen, fontSize = 13.sp)
    }
}
