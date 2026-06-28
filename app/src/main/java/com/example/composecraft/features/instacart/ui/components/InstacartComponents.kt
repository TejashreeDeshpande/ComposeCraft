package com.example.composecraft.features.instacart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.ui.theme.*

@Composable
fun ProductCard(
    product: Product,
    quantityInCart: Int = 0,
    onAddToCart: () -> Unit,
    onIncrement: () -> Unit = {},
    onDecrement: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(InstacartBg),
                contentAlignment = Alignment.Center
            ) {
                Text(product.imageEmoji, fontSize = 52.sp)
                if (product.isOrganic) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(6.dp)
                            .background(InstacartGreen, RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text("Organic", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
                if (product.originalPrice != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .background(InstacartBadge, CircleShape)
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        val pct = ((1 - product.price / product.originalPrice) * 100).toInt()
                        Text("-$pct%", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = InstacartTextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )
                Text(
                    text = product.unit,
                    fontSize = 11.sp,
                    color = InstacartTextSecondary,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = InstacartYellow, modifier = Modifier.size(11.dp))
                    Text(" ${product.rating}", fontSize = 11.sp, color = InstacartTextSecondary)
                }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "$${String.format("%.2f", product.price)}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = InstacartTextPrimary
                        )
                        if (product.originalPrice != null) {
                            Text(
                                text = "$${String.format("%.2f", product.originalPrice)}",
                                fontSize = 11.sp,
                                color = InstacartTextSecondary,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                    if (quantityInCart == 0) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(InstacartGreen, CircleShape)
                                .clickable { onAddToCart() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    } else {
                        QuantityStepper(quantity = quantityInCart, onIncrement = onIncrement, onDecrement = onDecrement, compact = true)
                    }
                }
            }
        }
    }
}

@Composable
fun QuantityStepper(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    compact: Boolean = false
) {
    val size = if (compact) 26.dp else 36.dp
    val iconSize = if (compact) 14.dp else 20.dp
    val textSize = if (compact) 13.sp else 16.sp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(if (compact) 4.dp else 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(
                    if (compact) InstacartGreenLight else InstacartGreen,
                    CircleShape
                )
                .border(1.dp, InstacartGreen, CircleShape)
                .clickable { onDecrement() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Remove,
                contentDescription = "Remove",
                tint = InstacartGreen,
                modifier = Modifier.size(iconSize)
            )
        }
        Text(
            text = "$quantity",
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = InstacartTextPrimary
        )
        Box(
            modifier = Modifier
                .size(size)
                .background(InstacartGreen, CircleShape)
                .clickable { onIncrement() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(iconSize))
        }
    }
}

@Composable
fun SectionHeader(title: String, actionLabel: String? = null, onAction: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = InstacartTextPrimary)
        if (actionLabel != null && onAction != null) {
            Text(
                actionLabel,
                fontSize = 14.sp,
                color = InstacartGreen,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onAction() }
            )
        }
    }
}

@Composable
fun PriceRow(label: String, value: String, bold: Boolean = false, color: Color = InstacartTextPrimary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 14.sp, color = if (bold) color else InstacartTextSecondary, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal)
        Text(value, fontSize = 14.sp, color = color, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal)
    }
}
