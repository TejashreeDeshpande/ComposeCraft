package com.example.composecraft.features.instacart.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.CartItem
import com.example.composecraft.features.instacart.presentation.viewmodel.CartViewModel
import com.example.composecraft.features.instacart.ui.components.PriceRow
import com.example.composecraft.features.instacart.ui.components.QuantityStepper
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(onBack: () -> Unit, onCheckout: () -> Unit) {
    val vm: CartViewModel = koinViewModel()
    val state by vm.state.collectAsState()
    val cart = state.cart

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        bottomBar = {
            if (cart.items.isNotEmpty()) {
                Surface(shadowElevation = 8.dp, color = InstacartSurface) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PriceRow("Subtotal", "$${String.format("%.2f", cart.subtotal)}")
                        PriceRow(
                            "Delivery fee",
                            if (cart.deliveryFee == 0.0) "FREE 🎉" else "$${String.format("%.2f", cart.deliveryFee)}",
                            color = if (cart.deliveryFee == 0.0) InstacartGreen else InstacartTextPrimary
                        )
                        PriceRow("Service fee", "$${String.format("%.2f", cart.serviceFee)}")
                        if (cart.discount > 0) {
                            PriceRow("Promo discount", "-$${String.format("%.2f", cart.discount)}", color = InstacartGreen)
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = InstacartDivider)
                        PriceRow("Total", "$${String.format("%.2f", cart.total)}", bold = true)
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = onCheckout,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen)
                        ) {
                            Text("Proceed to Checkout", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        },
        containerColor = InstacartBg
    ) { padding ->
        if (cart.items.isEmpty()) {
            EmptyCart(onBack = onBack, modifier = Modifier.padding(padding))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    DeliveryBanner(subtotal = cart.subtotal)
                }

                items(cart.items, key = { it.product.id }) { item ->
                    CartItemCard(
                        cartItem = item,
                        onIncrement = { vm.updateQuantity(item.product.id, item.quantity + 1) },
                        onDecrement = { vm.updateQuantity(item.product.id, item.quantity - 1) },
                        onRemove = { vm.removeItem(item.product.id) }
                    )
                }

                item {
                    PromoCodeSection(
                        input = state.promoInput,
                        onInputChange = vm::onPromoInput,
                        onApply = vm::applyPromo,
                        error = state.promoError,
                        success = state.promoSuccess,
                        appliedCode = cart.promoCode
                    )
                }

                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
private fun DeliveryBanner(subtotal: Double) {
    val remaining = 35.0 - subtotal
    val progress = (subtotal / 35.0).coerceIn(0.0, 1.0)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(InstacartGreenLight)
            .padding(14.dp)
    ) {
        Column {
            if (remaining > 0) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocalShipping, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Add $${String.format("%.2f", remaining)} more for FREE delivery!",
                        fontSize = 13.sp,
                        color = InstacartGreenDark,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🎉", fontSize = 16.sp)
                    Spacer(Modifier.width(8.dp))
                    Text("You've unlocked FREE delivery!", fontSize = 13.sp, color = InstacartGreenDark, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress.toFloat() },
                modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                color = InstacartGreen,
                trackColor = InstacartGreen.copy(alpha = 0.2f)
            )
        }
    }
}

@Composable
private fun CartItemCard(
    cartItem: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.size(72.dp).background(InstacartBg, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(cartItem.product.imageEmoji, fontSize = 36.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(cartItem.product.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 18.sp)
                Text(cartItem.product.unit, fontSize = 12.sp, color = InstacartTextSecondary, modifier = Modifier.padding(top = 2.dp))
                Text(
                    "$${String.format("%.2f", cartItem.product.price * cartItem.quantity)}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = InstacartTextPrimary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.DeleteOutline, contentDescription = "Remove", tint = InstacartTextSecondary, modifier = Modifier.size(18.dp))
                }
                QuantityStepper(quantity = cartItem.quantity, onIncrement = onIncrement, onDecrement = onDecrement, compact = true)
            }
        }
    }
}

@Composable
private fun PromoCodeSection(
    input: String,
    onInputChange: (String) -> Unit,
    onApply: () -> Unit,
    error: String?,
    success: Boolean,
    appliedCode: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Default.Sell, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(20.dp))
                Text("Promo Code", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }
            if (appliedCode != null) {
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(InstacartGreenLight, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(18.dp))
                        Text("\"$appliedCode\" applied!", fontSize = 13.sp, color = InstacartGreenDark, fontWeight = FontWeight.Medium)
                    }
                }
            } else {
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = input,
                        onValueChange = onInputChange,
                        placeholder = { Text("Enter code (try SAVE10)", fontSize = 13.sp) },
                        modifier = Modifier.weight(1f).height(52.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = InstacartGreen,
                            unfocusedBorderColor = InstacartDivider,
                            errorBorderColor = InstacartRed
                        ),
                        isError = error != null,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { onApply() })
                    )
                    Button(
                        onClick = onApply,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen),
                        modifier = Modifier.height(52.dp)
                    ) {
                        Text("Apply", fontWeight = FontWeight.Bold)
                    }
                }
                if (error != null) {
                    Text(error, fontSize = 12.sp, color = InstacartRed, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
    }
}

@Composable
private fun EmptyCart(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🛒", fontSize = 72.sp)
        Spacer(Modifier.height(16.dp))
        Text("Your cart is empty", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Add items to get started", fontSize = 15.sp, color = InstacartTextSecondary, modifier = Modifier.padding(top = 8.dp))
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(48.dp).padding(horizontal = 16.dp)
        ) {
            Text("Start Shopping", fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}
